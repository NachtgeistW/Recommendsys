package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.*;
import cn.iwyu.service.CommentService;
import cn.iwyu.service.RestaurantService;
import cn.iwyu.utils.Imgupload;
import cn.iwyu.utils.StringToList;
import cn.iwyu.utils.UrlImgUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName RestaurantController
 * @Description
 * @Author XiaoMao
 * @Date 3/10/2020 上午8:41
 * @Version 1.0
 **/
@Controller
//@Scope("prototype")
@RequestMapping("/restaurant")
public class RestaurantController {
    @Resource
    private RestaurantService service;
    @Resource
    private CommentService commentService;

    @RequestMapping("/findAll")
    @ResponseBody
    public Msg findAll(){
        List<RestaurantCustom> restaurants = service.findAll();
        if(restaurants.size()>0){
            return Msg.succeed().add(restaurants,restaurants.size());
        }
        return Msg.fail("无数据");
    }
    /**
    *@Description 查询所有未审核通过的餐馆（审核属性值为0的）
    *@Author XiaoMao
    *@Date 7/10/2020 上午10:19
    *@Param []
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("/checkRecommend")
    @ResponseBody
    public Msg checkRecommend(){
        List<RestaurantCustom> restaurantCustoms = service.checkRecommend();
        if(restaurantCustoms.size()>0){
            return Msg.succeed().add(restaurantCustoms,restaurantCustoms.size());
        }
        return Msg.fail("全部餐馆均审核通过");
    }
    @RequestMapping("/findByExample")
    @ResponseBody
    public Msg findByExample(RestaurantExample example){
        List<RestaurantCustom> restaurantCustoms = service.findByExample(example);
        if(restaurantCustoms.size()>0){
            return Msg.succeed().add(restaurantCustoms,restaurantCustoms.size());
        }
        return Msg.fail("数据库中无该数据");
    }
    @RequestMapping("/findById")
    @ResponseBody
    public Msg findById(Integer idRestaurant){
        Restaurant restaurant = service.findById(idRestaurant);
        if(restaurant==null){
            return Msg.fail("获取数据失败");
        }
        restaurant.setResturantImage(UrlImgUtil.change(restaurant.getResturantImage()));
        List<Restaurant> list = new ArrayList<>();
        list.add(restaurant);
        String score = commentService.getScore(idRestaurant);
        return Msg.succeed(score).add(list,list.size());
    }
    /**
    *@Description 修改餐馆信息
    *@Author XiaoMao
    *@Date 7/10/2020 上午10:18
    *@Param [restaurant]
    *Return cn.iwyu.domain.Msg
    **/
//    @RequestMapping(value = "/update",method= RequestMethod.POST,produces="application/json;charset=utf-8")
//    public @ResponseBody Msg update(Integer idRestaurant,String name,String intro,String typeOfCuisine,String address){
//        Restaurant restaurant = service.findById(idRestaurant);
//        restaurant.setAddress(address);
//        restaurant.setIntro(intro);
//        restaurant.setName(name);
//        restaurant.setTypeOfCuisine(typeOfCuisine);
//        System.out.println(restaurant);
//        Integer flag = service.update(restaurant);
//        if(flag==1){
//            return Msg.succeed();
//        }
//        return Msg.fail();
//    }
    @RequestMapping(value = "/update",method= RequestMethod.POST,produces="application/json;charset=utf-8")
    public @ResponseBody Msg update(Restaurant restaurant){
        Restaurant r = service.findById(restaurant.getIdRestaurant());
        restaurant.setIsAuditPassed(r.getIsAuditPassed());
        restaurant.setRecommendTime(r.getRecommendTime());
        restaurant.setResturantImage(r.getResturantImage());
        Integer flag = service.update(restaurant);
        if(flag==1){
            return Msg.succeed();
        }
        return Msg.fail("数据更新失败");
    }

    @RequestMapping(value = "/uploadImg" ,produces="application/json;charset=utf-8")
    @ResponseBody
    public synchronized JSONObject uploadImg( @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, @RequestParam(value = "id", required = false) Integer resId) throws Exception {
        Object lock = new Object();
        Map<String,Object> result =  new HashMap<String, Object>();
//        synchronized (lock)
//        {
            Restaurant restaurant = service.findById(resId);
            System.out.println(restaurant.getResturantImage());
            Imgupload imgupload = new Imgupload();
            String str = null;
            result = imgupload.uploadAreaFile(file,request);
            if(restaurant.getResturantImage()!=null){
                str = restaurant.getResturantImage();
                str = str + result.get("filePath") + ",";

            }else {
                str = result.get("filePath") + ",";
//                System.out.println(2);
            }
//            System.out.println(str+"1");
////            System.out.println(resId);
//        System.out.println(3);
            restaurant.setResturantImage(str);
            service.update(restaurant);

//        System.out.println(restaurant.getResturantImage()+"2");
//
//        }
        return JSONObject.fromObject(result);
    }
/**
*@Description 记得整合时恢复用户id的设置
*@Author XiaoMao
*@Date 15/11/2020 下午5:20
*@Param [restaurant, session]
*Return cn.iwyu.domain.Msg
**/
    @RequestMapping(value = "/save" ,produces="application/json;charset=utf-8")
    @ResponseBody
    public Msg save(@RequestBody Restaurant restaurant, HttpSession session) throws Exception {
        Date date = new Date();
        if(restaurant.getName() == null){

            return Msg.fail("添加失败");
        }
//        restaurant.setResturantImage(imgupload.uploadMultipal(files,request));


        restaurant.setRecommendTime(date);
        restaurant.setIdRecommandedUser((Integer) session.getAttribute("userID"));
        if(session.getAttribute("role")=="1"){
            restaurant.setIsAuditPassed(1);
        }else {
            restaurant.setIsAuditPassed(0);
        }
        Integer flag = service.save(restaurant);
        if(flag==0){
            return Msg.fail();
        }
        return Msg.succeed(Integer.toString(restaurant.getIdRestaurant()));
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Msg delete(Integer idRestaurant){
        Integer flag = service.delete(idRestaurant);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }
    /**
    *@Description 餐馆审核通过
    *@Author XiaoMao
    *@Date 7/10/2020 上午10:57
    *@Param [restaurant]
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("/pass")
    @ResponseBody
    public Msg pass(Integer idRestaurant,HttpSession session){
        Integer userID = (Integer) session.getAttribute("userID");
        System.out.println(idRestaurant);
        Restaurant restaurant = service.findById(idRestaurant);
        if(restaurant==null){
            return Msg.fail("查无此餐馆");
        }
        restaurant.setIsAuditPassed(userID);
        Integer flag = service.update(restaurant);
        if(flag==1){
            return Msg.succeed("通过");
        }
        return Msg.fail();
    }
/**
*@Description 批量删除
*@Author XiaoMao
*@Date 7/10/2020 下午3:03
*@Param [restaurants]
*Return cn.iwyu.domain.Msg
**/
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Msg batchDelete(String ids){
        List<Integer> list = StringToList.change(ids);
        if(list!=null){
            Integer flag = service.batchDelete(list);
            if(flag>0){
                return Msg.succeed("删除成功");
            }
        }
        return  Msg.fail("删除失败");
    }

    @RequestMapping("/recommend")
    @ResponseBody
    public Msg recommend(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        List<RecommendRes> recommendRes = new ArrayList<>();
        if(userId==null){
            recommendRes = service.passRecommend(10);
            return Msg.succeed().add(recommendRes,recommendRes.size());
        }

        return Msg.fail("推荐失败");
    }
}
