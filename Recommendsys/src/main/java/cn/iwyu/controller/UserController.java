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
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public Msg findAll(){
        List<User> users = userService.findAll();
        if(users.size()>0){
            return Msg.succeed().add(users,users.size());
        }
        return Msg.fail();
    }


}
