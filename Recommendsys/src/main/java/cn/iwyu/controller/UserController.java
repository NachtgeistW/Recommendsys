package cn.iwyu.controller;/**
 * Created by Chester on 2/10/2020.
 */

import cn.iwyu.domain.Msg;
import cn.iwyu.domain.User;
import cn.iwyu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
        List<User> users = service.findByExample(example);
        if(users.size()>0){
            return Msg.succeed().add(users,users.size());
        }
        return Msg.fail();
    }

    @RequestMapping("/save")
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
        Integer flag = service.delete(idUser);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }
    @RequestMapping("/update")
    @ResponseBody
    public Msg update(User user){
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
    public Msg batchDelete(List<User> users){
        Integer flag = service.batchDelete(users);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }
}
