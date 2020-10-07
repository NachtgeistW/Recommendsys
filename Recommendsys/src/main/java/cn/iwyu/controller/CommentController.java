package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
import cn.iwyu.domain.CommentExample;
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

    @RequestMapping("/findByExample")
    @ResponseBody
    public Msg findByExample(CommentExample example){
        List<CommentCustom> commentCustoms = service.findByExample(example);
        Integer count = commentCustoms.size();
        if(count>0){
            return Msg.succeed().add(commentCustoms,count);
        }
        return Msg.fail();
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Msg delete(Integer idComment){
        Integer flag = service.delete(idComment);
        if(flag>0){
            return  Msg.succeed();
        }
        return Msg.fail();
    }
/**
*@Description 批量删除
*@Author XiaoMao
*@Date 7/10/2020 下午3:35
*@Param [comments]
*Return cn.iwyu.domain.Msg
**/
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Msg batchDelete(List<Comment> comments){
        Integer flag = service.batchDelete(comments);
        if(flag>0){
            return  Msg.succeed();
        }
        return Msg.fail();
    }
}
