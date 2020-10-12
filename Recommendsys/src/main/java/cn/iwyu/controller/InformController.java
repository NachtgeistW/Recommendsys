package cn.iwyu.controller;/**
 * Created by Chester on 12/10/2020.
 */

import cn.iwyu.domain.Inform;
import cn.iwyu.domain.Msg;
import cn.iwyu.service.InformService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @ClassName InformController
 * @Description 通知
 * @Author XiaoMao
 * @Date 12/10/2020 下午3:48
 * @Version 1.0
 **/
@Controller
@RequestMapping("/Inform")
public class InformController {
    @Resource
    private InformService service;

    @RequestMapping("/send")
    @ResponseBody
    public Msg send(Inform inform){
        Date date = new Date();
        inform.setDate(date);
        inform.setRead(0);
        Integer flag = service.sendInform(inform);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }

    @RequestMapping("userFindAll")
    @ResponseBody
    public Msg userFindAll(HttpSession session){
        List<Inform> informs = service.userFindAll((Integer)session.getAttribute("userID"));
        if(informs.size()>0){
            return Msg.succeed().add(informs,informs.size());
        }
        return Msg.fail();
    }

    @RequestMapping("/readAll")
    @ResponseBody
    public Msg readAll(HttpSession session){
        Integer flag = service.readAll((Integer)session.getAttribute("userID"));
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Msg delete(Integer infoemId){
        Integer flag = service.delete(infoemId);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }
}
