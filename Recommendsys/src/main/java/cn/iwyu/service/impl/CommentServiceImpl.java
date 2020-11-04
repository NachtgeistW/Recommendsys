package cn.iwyu.service.impl;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.dao.CommentCustomMapper;
import cn.iwyu.dao.CommentMapper;
import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
import cn.iwyu.domain.CommentExample;
import cn.iwyu.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 上午10:08
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;
    @Resource
    CommentCustomMapper commentCustomMapper;

    @Override
    public Integer save(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public List<CommentCustom> findAll() {
        List<CommentCustom> commentCustoms = commentCustomMapper.findAll();
        for (CommentCustom c:commentCustoms
             ) {
            c.setUserName(c.getUser().getUserName());
            c.setRestaurantName(c.getRestaurant().getName());
        }
        return commentCustoms;
    }

    @Override
    public List<CommentCustom> findByUserId(Integer userId) {
        return commentCustomMapper.findByUserId(userId);
    }

    @Override
    public List<CommentCustom> findByResId(Integer resId) {
        return commentCustomMapper.findByResId(resId);
    }

    @Override
    public int update(Comment comment) {
        return commentMapper.updateByPrimaryKey(comment);
    }

    @Override
    public int delete(Integer commId) {
        return commentMapper.deleteByPrimaryKey(commId);
    }

    @Override
    public List<CommentCustom> findByExample(CommentExample example) {
        List<CommentCustom> commentCustoms = commentCustomMapper.findByExample(example);
        for (CommentCustom c:commentCustoms
        ) {
            c.setUserName(c.getUser().getUserName());
            c.setRestaurantName(c.getRestaurant().getName());
        }
        return commentCustoms;
    }

    @Override
    public Integer batchDelete(List<Integer> ids) {
        Integer flag = 0;
        for (Integer id :ids) {
            flag += commentMapper.deleteByPrimaryKey(id);
        }

        return flag;
    }

    @Override
    public Integer scoreRestaurant(Comment comment) {
        Integer user_id = comment.getIdUser();
        Integer res_id = comment.getIdRestaurant();
        Integer score = comment.getScore();
        List<CommentCustom> commentCustoms = commentCustomMapper.findScore(user_id,res_id);
        //查到用户是否评论过这个餐馆，评论过就看是否已经评分过，评分过不能再次评分
        if(commentCustoms!=null){
            for (CommentCustom commentCustom :commentCustoms) {
                if(commentCustom.getScore()!=null){
                    return 0;
                }
            }
        }
        return  commentMapper.insert(comment);
    }

    @Override
    public Comment findById(Integer comment_id) {
        return commentMapper.selectByPrimaryKey(comment_id);
    }
}
