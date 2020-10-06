package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.CommentCustom;
import cn.iwyu.domain.Msg;
import cn.iwyu.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CommentContrllor
 * @Description
 * @Author XiaoMao
 * @Date 3/10/2020 上午11:11
 * @Version 1.0
 **/
@Controller
@RequestMapping("/Comment")
public class CommentController {
    @Resource
    private CommentService service;

    @RequestMapping("/findAll")
    @ResponseBody
    public Msg findAll(){
        List<CommentCustom> commentCustoms = service.findAll();
        if(commentCustoms.size()>0){
            return Msg.succeed().add(commentCustoms,commentCustoms.size());
        }
        return Msg.fail();
    }


}
