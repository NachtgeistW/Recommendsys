package cn.iwyu.controller;/**
 * Created by Chester on 2/10/2020.
 */

import cn.iwyu.domain.Msg;
import cn.iwyu.domain.User;
import cn.iwyu.service.UserService;
import cn.iwyu.utils.StringToList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author XiaoMao
 * @Date 2/10/2020 下午2:52
 * @Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService service;

    @RequestMapping("/findAll")
    @ResponseBody
    public Msg findAll(){
        List<User> users = service.findAll();
//        System.out.println(users.get(0));
        if(users.size()>0){
            return Msg.succeed().add(users,users.size());
        }
        return Msg.fail();
    }
    /**
    *@Description 模糊查询
    *@Author XiaoMao
    *@Date 7/10/2020 下午2:17
    *@Param [example]
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("/findByExample")
    @ResponseBody
    public Msg findByExample(User example){
        System.out.println(example);
        List<User> users = service.findByExample(example);
        if(users.size()>0){
            return Msg.succeed().add(users,users.size());
        }
        return Msg.fail("没有查询到相关的数据");
    }

    @GetMapping("/saveOrUpdate")
    @ResponseBody
    public Msg save(User user){
        Integer flag = service.save(user);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Msg delete(Integer idUser){
        if(idUser!=null){
            Integer flag = service.delete(idUser);
            if(flag>0){
                return Msg.succeed("删除成功");
            }
        }
        return Msg.fail("删除失败");
    }
    /**
    *@Description 用户端的修改：用户名
    *@Author XiaoMao
    *@Date 10/10/2020 下午4:25
    *@Param [user]
    *Return cn.iwyu.domain.Msg
    **/
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Msg updateUser(String userName, HttpSession session){
        User user = service.findById((Integer)session.getAttribute("userID"));
        user.setUserName(userName);
        Integer flag = service.update(user);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }
    /**
    *@Description 管理员只能修改某个用户的身份，给予或收回管理员权限
    *@Author XiaoMao
    *@Date 10/10/2020 下午4:29
    *@Param [userName, session]
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public Msg updateAdmin(Integer userId,Integer role){
        User user = service.findById(userId);
        user.setIdentity(role);
        Integer flag = service.update(user);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }

    /**
    *@Description 批量删除用户
    *@Author XiaoMao
    *@Date 7/10/2020 下午2:57
    *@Param [users]
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Msg batchDelete(String ids){
        List<Integer> list = StringToList.change(ids);
        if(list!=null){
            Integer flag = service.batchDelete(list);
            if(flag>0){
                return Msg.succeed("批量删除成功");
            }
        }
        return Msg.fail("批量删除失败");
    }
}
