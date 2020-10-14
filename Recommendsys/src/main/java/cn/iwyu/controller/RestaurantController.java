package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.*;
import cn.iwyu.service.RestaurantService;
import cn.iwyu.utils.Imgupload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
@RequestMapping("/restaurant")
public class RestaurantController {
    @Resource
    private RestaurantService service;

    @RequestMapping("/findAll")
    @ResponseBody
    public Msg findAll(){
        List<RestaurantCustom> restaurants = service.findAll();
        if(restaurants.size()>0){
            return Msg.succeed().add(restaurants,restaurants.size());
        }
        return Msg.fail();
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
        return Msg.fail();
    }
    @RequestMapping("/findByExample")
    @ResponseBody
    public Msg findByExample(RestaurantExample example){
        List<RestaurantCustom> restaurantCustoms = service.findByExample(example);
        if(restaurantCustoms.size()>0){
            return Msg.succeed().add(restaurantCustoms,restaurantCustoms.size());
        }
        return Msg.fail();
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
        System.out.println(1);
        Integer flag = service.update(restaurant);
        System.out.println(flag);
        if(flag==1){
            return Msg.succeed();
        }
        return Msg.fail();
    }
    @RequestMapping(value = "/save" ,method= RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
    public Msg save(Restaurant restaurant, HttpSession session,MultipartFile[] files, HttpServletRequest request) throws Exception {
        Date date = new Date();
        Imgupload imgupload = new Imgupload();
        restaurant.setResturantImage(imgupload.uploadMultipal(files,request));
        restaurant.setRecommendTime(date);
        if(session.getAttribute("role")=="1"){
            restaurant.setIsAuditPassed(1);
        }else {
            restaurant.setIsAuditPassed(0);
        }
        System.out.println("ok");
        Integer flag = service.save(restaurant);
        if(flag==1){
            return Msg.succeed();
        }
        return Msg.fail();
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
    public Msg pass(Integer idRestaurant){
        Restaurant restaurant = service.findById(idRestaurant);
        restaurant.setIsAuditPassed(1);
        Integer flag = service.update(restaurant);
        if(flag==1){
            return Msg.succeed();
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
        List<Integer> list1 = new ArrayList<Integer>();
        if (ids!=null &&!ids.equals("")) {
            String[] idsStrings = ids.split(",");//转成String数组
            int[] array = Arrays.stream(idsStrings).mapToInt(Integer::parseInt).toArray();//转int数组
            list1 = Arrays.stream(array).boxed().collect(Collectors.toList());//转List<Integer>
            System.out.println("到达");
            Integer flag = service.batchDelete(list1);
            if(flag>0){
                return Msg.succeed();
            }
        }
        return  Msg.fail();
    }
}
