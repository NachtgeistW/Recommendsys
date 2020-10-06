package cn.iwyu.domain;/**
 * Created by Chester on 3/10/2020.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RestaurantExample
 * @Description
 * @Author XiaoMao
 * @Date 3/10/2020 下午2:14
 * @Version 1.0
 **/

public class RestaurantExample implements Serializable {
    String r_name;
    String r_address;
    String r_cuisine;
    String startTime;
    String endTime;

    @Override
    public String toString() {
        return "RestaurantExample{" +
                "r_name='" + r_name + '\'' +
                ", r_address='" + r_address + '\'' +
                ", r_cuisine='" + r_cuisine + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_address() {
        return r_address;
    }

    public void setR_address(String r_address) {
        this.r_address = r_address;
    }

    public String getR_cuisine() {
        return r_cuisine;
    }

    public void setR_cuisine(String r_cuisine) {
        this.r_cuisine = r_cuisine;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
