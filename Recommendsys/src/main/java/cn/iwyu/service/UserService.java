package cn.iwyu.service;/**
 * Created by Chester on 29/9/2020.
 */

import cn.iwyu.domain.Email;
import cn.iwyu.domain.User;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @InterfaceName UserService
 * @Description
 * @Author XiaoMao
 * @Date 29/9/2020 下午3:39
 * @Version 1.0
 **/

public interface UserService {
    //增加用户
    public int save(User user);
    //通过id查询用户
    public User findById(Integer id_user);
    //查询所有用户
    public List<User> findAll();
    //修改用户
    public int update(User user);
    //删除用户
    public int delete(Integer id_user);
    //条件模糊查询
    public List<User> findByExample(User example);
    //批量删除
    public Integer batchDelete(List<User> users);
    //验证邮箱
    public Integer checkEmail(User user);
    //验证密码,并登录
    public Integer checkPwd(User user);
    //发送邮箱验证码，验证邮箱
    public String sendEmail(Email mail);
}
