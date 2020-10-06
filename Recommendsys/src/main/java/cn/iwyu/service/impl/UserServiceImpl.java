package cn.iwyu.service.impl;/**
 * Created by Chester on 29/9/2020.
 */

import cn.iwyu.dao.UserMapper;
import cn.iwyu.domain.User;
import cn.iwyu.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 29/9/2020 下午3:45
 * @Version 1.0
 **/
@Service
@Repository
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Transactional
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findById(Integer id_user) {
        return userMapper.selectByPrimaryKey(id_user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int delete(Integer id_user) {
        return userMapper.deleteByPrimaryKey(id_user);
    }
}
