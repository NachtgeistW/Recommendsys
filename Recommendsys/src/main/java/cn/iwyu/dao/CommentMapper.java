package cn.iwyu.dao;

import cn.iwyu.domain.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer idComment);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer idComment);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}