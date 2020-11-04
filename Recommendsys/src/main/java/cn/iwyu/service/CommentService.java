package cn.iwyu.service;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
import cn.iwyu.domain.CommentExample;

import java.util.List;

/**
 * @InterfaceName CommentService
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 上午10:00
 * @Version 1.0
 **/

public interface CommentService {
    //添加评论
    public Integer save(Comment comment);
    //通过id查询评论
    Comment findById(Integer comment_id);
    //查询全部评论
    public List<CommentCustom> findAll();
    //通过用户id查询评论
    public List<CommentCustom> findByUserId(Integer userId);
    //通过餐馆id查询评论
    public List<CommentCustom> findByResId(Integer resId);
    //修改评论
    public int update(Comment comment);
    //删除评论
    public int delete(Integer commId);
    //条件模糊查询
    public List<CommentCustom> findByExample(CommentExample example);
    //批量删除
    public Integer batchDelete(List<Integer> ids);
    //完成评分
    Integer scoreRestaurant(Comment comment);
}
