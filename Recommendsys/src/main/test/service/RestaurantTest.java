package service;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.Restaurant;
import cn.iwyu.service.RestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @ClassName RestaurantTest
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午5:33
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RestaurantTest {

    @Resource
    RestaurantService restaurantService;

    @Test
    public void save(){
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress("广州市北京路");
        restaurant.setIdRecommandedUser(3);
        restaurant.setName("北京路烤鸭");
        restaurant.setIsAuditPassed(1);
        restaurantService.save(restaurant);
    }
    @Test
    public void findById(){
        System.out.println(restaurantService.findById(2));
    }
    @Test
    public void findAll(){
        for (Restaurant r:restaurantService.findAll()
             ) {
            System.out.println(r);
        }
    }
    @Test
    public void update(){
        Restaurant restaurant = restaurantService.findById(2);
        restaurant.setName("南京路烤鸭");
        System.out.println(restaurantService.update(restaurant));
    }
    @Test
    public void delete(){
        System.out.println(restaurantService.delete(2));
    }
}
