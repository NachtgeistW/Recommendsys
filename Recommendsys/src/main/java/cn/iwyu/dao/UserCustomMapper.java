package cn.iwyu.dao;/**
 * Created by Chester on 7/10/2020.
 */

import cn.iwyu.domain.User;

import java.util.List;

/**
 * @InterfaceName UserCustom
 * @Description 脱离于逆向工程的方法
 * @Author XiaoMao
 * @Date 7/10/2020 下午2:19
 * @Version 1.0
 **/

public interface UserCustomMapper {
    //模糊查寻
    List<User> findByExample(User example);
    //验证邮箱
    User checkEmail(String email);
    //验证密码
    User checkPwd(User user);
}
