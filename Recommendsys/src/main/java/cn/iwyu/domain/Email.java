package cn.iwyu.domain;/**
 * Created by Chester on 7/10/2020.
 */

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @ClassName Email
 * @Description
 * @Author XiaoMao
 * @Date 7/10/2020 下午7:52
 * @Version 1.0
 **/
public class Email implements Serializable {
    private String address;

    private String title;

    private String text;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
