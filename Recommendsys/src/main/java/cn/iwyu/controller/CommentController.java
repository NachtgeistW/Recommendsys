package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
import cn.iwyu.domain.CommentExample;
import cn.iwyu.domain.Msg;
import cn.iwyu.service.CommentService;
import cn.iwyu.utils.StringToList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
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
        return Msg.fail("数据库中暂无数据");
    }
/**
*@Description 通过餐馆ID获取评论
*@Author XiaoMao
*@Date 29/11/2020 下午4:46
*@Param [resId]
*Return cn.iwyu.domain.Msg
**/
    @RequestMapping("/resComent")
    @ResponseBody
    public  Msg resComent(Integer resId){
        List<CommentCustom> commentCustoms= service.findByResId(resId);
        if(commentCustoms!=null){
            return Msg.succeed().add(commentCustoms,commentCustoms.size());
        }
        return Msg.fail("没有评论");
    }
    @RequestMapping("/findByExample")
    @ResponseBody
    public Msg findByExample(CommentExample example){
        System.out.println(example);
        List<CommentCustom> commentCustoms = service.findByExample(example);
        Integer count = commentCustoms.size();
        if(count>0){
            return Msg.succeed().add(commentCustoms,count);
        }
        return Msg.fail("没有找到拥有该条件的数据");
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Msg delete(Integer idComment){
        Integer flag = service.delete(idComment);
        if(flag>0){
            return  Msg.succeed("删除成功");
        }
        return Msg.fail("删除失败");
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
    public Msg batchDelete(String ids){
        List<Integer> list = StringToList.change(ids);
        if (list != null) {
            Integer flag = service.batchDelete(list);
            if(flag>0){
                return Msg.succeed("批量删除成功");
            }
        }

        return  Msg.fail("批量删除失败");
    }
    /**
    *@Description 评分
    *@Author XiaoMao
    *@Date 4/11/2020 下午3:15
    *@Param [comment]
    *Return java.lang.Integer
    **/
    @RequestMapping("/scoreRestaurant")
    @ResponseBody
    public Integer scoreRestaurant(Comment comment){
        Date date = new Date();
        comment.setTime(date);
        comment.setNumLike(0);
        Integer flag = 0;
        //评分不能为回复信息
        if(comment.getIdCommentReply()!=null){
            return flag;
        }
        flag = service.scoreRestaurant(comment);
        return flag;
    }
    /**
    *@Description 评论消息
    *@Author XiaoMao
    *@Date 4/11/2020 下午3:20
    *@Param [comment]
    *Return java.lang.Integer
    **/
    @RequestMapping("/restaueant")
    @ResponseBody
    public Integer restaueant(Comment comment){
        //单纯的评论不能带评分
        if(comment.getScore()!=null){
            return 0;
        }
        //回复的原评论需要存在
        if(service.findById(comment.getIdCommentReply())==null){
            return 0;
        }
        Date date = new Date();
        comment.setTime(date);
        comment.setNumLike(0);
        return service.save(comment);
    }
}
