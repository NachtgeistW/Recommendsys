package cn.iwyu.service.impl;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.dao.CommentCustomMapper;
import cn.iwyu.dao.CommentMapper;
import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
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
    public void save(Comment comment) {
        commentMapper.insert(comment);
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
}
