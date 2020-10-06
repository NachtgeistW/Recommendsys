package cn.iwyu.domain;/**
 * Created by Chester on 29/9/2020.
 */

import java.util.List;

/**
 * @ClassName RestaurantCustom
 * @Description
 * @Author XiaoMao
 * @Date 29/9/2020 下午11:55
 * @Version 1.0
 **/

public class RestaurantCustom extends Restaurant {
    private User user;
    private String userName;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
