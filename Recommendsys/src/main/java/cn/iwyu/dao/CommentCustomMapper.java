package cn.iwyu.dao;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
import cn.iwyu.domain.CommentExample;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @InterfaceName CommentCustomMapper
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 上午8:44
 * @Version 1.0
 **/
@Repository
public interface CommentCustomMapper {
    //查询所有评论，直接显示用户名和店铺名
    List<CommentCustom> findAll();
    //查询通过餐馆id查询评论，应该返回一个集合
    List<CommentCustom> findByResId(Integer resId);
    //查询通过餐馆id查询评分
    List<CommentCustom> getScore(Integer resId);
    //通过用户id查询评论，应该返回一个集合
    List<CommentCustom> findByUserId(Integer userId);
    //条件模糊查询
    List<CommentCustom> findByExample(CommentExample example);
    //查询某一位用户是否已经评分过了
    List<CommentCustom> findScore(@Param("userId") Integer userId,@Param("resId") Integer resId);
}
