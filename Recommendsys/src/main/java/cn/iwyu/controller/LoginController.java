package cn.iwyu.controller;/**
 * Created by Chester on 2/10/2020.
 */

import cn.iwyu.domain.Email;
import cn.iwyu.domain.Msg;
import cn.iwyu.domain.User;
import cn.iwyu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//    @RequestMapping("/loginUser")
    @ResponseBody
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public Msg login(HttpSession session,String password,String email){
        User user = new User();
        char data = '0';
        System.out.println(email);
        user.setEmail(email);
        user.setPassword(password);
        user = service.checkPwd(user);
        System.out.println(user);
        if(user!=null) {
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userID", user.getIdUser());
            session.setAttribute("role", user.getIdentity());
            session.setAttribute("loginFlag", 1);
            if (user.getIdentity() == 1) {
                return Msg.succeed("1");
            } else if (user.getIdentity() == 0) {
                return Msg.succeed("0");
            }
        }
        return Msg.fail("-1");
    }

    /**
    *@Description 注册（邮箱不存在）和改密码（邮箱存在）都需要验证邮箱
    *@Author XiaoMao
    *@Date 7/10/2020 下午8:20
    *@Param [user]
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("/checkEmail")
    @ResponseBody
    public Msg checkMail(String email){
        //验证邮箱是否已经存在，不存在的话，需要提醒用户先去注册
        if(service.checkEmail(email)==null){
            return Msg.succeed("邮箱不存在");
        }
        return Msg.succeed("邮箱已存在");
    }

    @RequestMapping("/sendEmail")
    public ModelAndView sendEmail(@RequestBody String address, HttpSession session){
        Email email = new Email();
        email.setAddress(address);
//        System.out.println(email.getAddress());
        ModelAndView mv = new ModelAndView();
        String code = service.sendEmail(email);
//        mv.setViewName("loginRegister");
        boolean result = false;
        if(code != null){
            result =true;
            session.setAttribute("code",code);
            session.setAttribute("address",address);
            mv.addObject("result",result);
            return mv;
        }
        mv.addObject("result",result);
        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView register(User user,String emailCaptcha,HttpSession session){
        user.setIdentity(2);
//        System.out.println(user);
        boolean result = false;
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginRegister");
        if(emailCaptcha.equals(session.getAttribute("code"))&&user.getEmail().equals(session.getAttribute("address"))){
            Integer flag = service.save(user);
            System.out.println(flag);
            if(flag>0){
                result =true;
                mv.addObject("result",result);
                return mv;
            }
        }

        mv.addObject("result",result);
        return mv;
    }
/**
*@Description 传入邮箱、新密码、验证码，后台需要先验证这个邮箱是否存在
*@Author XiaoMao
*@Date 7/10/2020 下午8:14
*@Param [code, user, request]
*Return cn.iwyu.domain.Msg
**/
    @RequestMapping("/alterPwd")
    public ModelAndView alterPwd(String FEmailCaptcha,String fEmail,String password,HttpSession session){
        boolean result = false;
        ModelAndView mv = new ModelAndView();
        User user = service.checkEmail(fEmail);
        mv.setViewName("login");
        System.out.println(user);
        if(user==null){
            System.out.println("邮箱不存在");
            mv.addObject("result",result);
            return mv;
        }else {
            user.setPassword(password);
            if(FEmailCaptcha.equals(session.getAttribute("code"))&&fEmail.equals(session.getAttribute("address"))){
                Integer flag = service.update(user);
                System.out.println(user);
                if(flag>0){
                    result = true;
                    mv.addObject("result",result);
                    return mv;
                }
            }
        }
        mv.addObject("result",result);
        return mv;
    }

}
