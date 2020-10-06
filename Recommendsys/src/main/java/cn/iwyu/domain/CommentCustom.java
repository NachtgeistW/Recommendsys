package cn.iwyu.domain;/**
 * Created by Chester on 30/9/2020.
 */

/**
 * @ClassName CommentCustom
 * @Description 用于补充评论表的多表查询
 * @Author XiaoMao
 * @Date 30/9/2020 上午8:42
 * @Version 1.0
 **/

public class CommentCustom extends Comment {
    private User user;
    private String userName;
    private Restaurant restaurant;
    private String restaurantName;
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
        return "CommentCustom{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
