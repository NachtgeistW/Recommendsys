package cn.iwyu.dao;

import cn.iwyu.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer idUser);

    int insert(User record);

    User selectByPrimaryKey(Integer idUser);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}