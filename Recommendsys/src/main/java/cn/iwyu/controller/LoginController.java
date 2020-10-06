package cn.iwyu.controller;/**
 * Created by Chester on 2/10/2020.
 */

import cn.iwyu.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName LoginController
 * @Description
 * @Author XiaoMao
 * @Date 2/10/2020 下午3:09
 * @Version 1.0
 **/
@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public Msg login(){
        return Msg.succeed();
    }
}
