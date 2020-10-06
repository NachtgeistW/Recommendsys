package cn.iwyu.domain;/**
 * Created by Chester on 30/9/2020.
 */

import org.springframework.stereotype.Repository;

/**
 * @ClassName ComlainRecordCustom
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午3:54
 * @Version 1.0
 **/
@Repository
public class ComplainRecordCustom extends ComplainRecord {
    private User user;
    private Restaurant restaurant;
    private String userName;
    private String restaurantName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return "ComlainRecordCustom{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
