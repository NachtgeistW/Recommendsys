package cn.iwyu.controller;/**
 * Created by Chester on 2/10/2020.
 */

import cn.iwyu.domain.Email;
import cn.iwyu.domain.Msg;
import cn.iwyu.domain.User;
import cn.iwyu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName LoginController
 * @Description
 * @Author XiaoMao
 * @Date 2/10/2020 下午3:09
 * @Version 1.0
 **/
@Controller
public class LoginController {

    @Resource
    UserService service;

    @RequestMapping("/login")
    @ResponseBody
    public Msg login(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        Integer flag = service.checkPwd(user);
        if(flag>0){
            session.setAttribute("userName",user.getUserName());
            session.setAttribute("userID",user.getIdUser());
            session.setAttribute("loginFlag",1);
            return Msg.succeed();
        }
        return Msg.fail();
    }

    /**
    *@Description 注册（邮箱不存在）和改密码（邮箱存在）都需要验证邮箱
    *@Author XiaoMao
    *@Date 7/10/2020 下午8:20
    *@Param [user]
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("checkEmail")
    @ResponseBody
    public Msg checkMail(User user){
        //验证邮箱是否已经存在，不存在的话，需要提醒用户先去注册
        if(service.checkEmail(user)==0){
            return Msg.succeed("邮箱不存在");
        }
        return Msg.succeed("邮箱已存在");
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public Msg sendEmail(Email mail,HttpServletRequest request){
        String code = service.sendEmail(mail);
        if(code != null){
            HttpSession session = request.getSession();
            session.setAttribute("code",code);
            return Msg.succeed();
        }
        return Msg.fail();
    }

    @RequestMapping("/register")
    @ResponseBody
    public Msg register(User user,String code,HttpServletRequest request){
        HttpSession session = request.getSession();
        if(code==session.getAttribute("code")){
            Integer flag = service.save(user);
            if(flag>0){
                return Msg.succeed();
            }
            return Msg.fail();
        }
        return Msg.fail();
    }
/**
*@Description 传入邮箱、新密码、验证码，后台需要先验证这个邮箱是否存在
*@Author XiaoMao
*@Date 7/10/2020 下午8:14
*@Param [code, user, request]
*Return cn.iwyu.domain.Msg
**/
    @RequestMapping("/alterPwd")
    @ResponseBody
    public Msg alterPwd(String code,User user,HttpServletRequest request){
        HttpSession session = request.getSession();
        if(code==session.getAttribute("code")){
            Integer flag = service.save(user);
            if(flag>0){
                return Msg.succeed();
            }
            return Msg.fail();
        }
        return Msg.fail();
    }

}
