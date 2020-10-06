package service;/**
 * Created by Chester on 29/9/2020.
 */

import cn.iwyu.domain.User;
import cn.iwyu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;



/**
 * @ClassName UserTest
 * @Description
 * @Author XiaoMao
 * @Date 29/9/2020 下午4:00
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

    private User user = new User();
    @Resource
    UserService userService;

    @Test
    public void save(){
        user.setEmail("1985153396@qq.com");
        user.setPassword("123456");
        user.setUserName("AA");
        user.setExperience(1);
        user.setIdentity(1);
        user.setIntegral(1);
        userService.save(user);
    }

    @Test
    public void findById(){
        System.out.println(userService.findById(1));
    }

    @Test
    public void findAll(){
        for (User user:userService.findAll()
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void update(){
        User user = new User();
        user.setEmail("886@qq.com");
        user.setPassword("123456");
        user.setUserName("chester");
        user.setExperience(1);
        user.setIdentity(1);
        user.setIntegral(1);
        user.setIdUser(1);
        System.out.println(userService.update(user));
    }

    @Test
    public void delete(){
        System.out.println(userService.delete(2));
    }
}
