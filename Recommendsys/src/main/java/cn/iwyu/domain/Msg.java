package cn.iwyu.domain;/**
 * Created by Chester on 30/9/2020.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Msg
 * @Description 通用的消息返回类
 * @Author XiaoMao
 * @Date 30/9/2020 下午7:39
 * @Version 1.0
 **/

public class Msg {
    //状态码
    private String code;
    //提示信息
    private String msg;
    //查找返回的信息
    private List<?> data;

    private Integer count;

    //静态方法添加成功或失败信息
    public static Msg succeed(){
        Msg msg = new Msg();
        msg.setCode("0");
        msg.setMsg("succeed");
        return msg;
    }
    public static Msg succeed(String text){
        Msg msg = new Msg();
        msg.setCode("0");
        msg.setMsg(text);
        return msg;
    }
    public static Msg fail(){
        Msg msg = new Msg();
        msg.setCode("0");
        msg.setCount(0);
        msg.setMsg("fail");
        return msg;
    }
    public Msg add(List<?> rows,Integer count){
        this.data = rows;
        this.setCount(count);
        return this;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
