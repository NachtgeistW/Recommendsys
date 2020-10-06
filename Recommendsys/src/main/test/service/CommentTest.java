package service;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
import cn.iwyu.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @ClassName CommentTest
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 上午10:13
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommentTest {

    @Resource
    CommentService commentService;

    @Test
    public void save(){
        Comment comment = new Comment();
        comment.setIdUser(3);
        comment.setIdRestaurant(1);
        comment.setNumLike(1);
        comment.setContext("无敌");
        comment.setIdCommentReply(3);
        comment.setScore(5);
        commentService.save(comment);
    }

    @Test
    public void findAll(){
        for (Comment c:commentService.findAll()
             ) {
            System.out.println(c);
        }
    }

    @Test
    public void findByUserId(){
        for (Comment c :commentService.findByUserId(1)) {
            System.out.println(c);
        }
    }
    @Test
    public void findByResId(){
        for (Comment c:commentService.findByResId(1)
             ) {
            System.out.println(c);
        }
    }
}
