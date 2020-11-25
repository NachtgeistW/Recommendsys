package cn.iwyu.domain;/**
 * Created by Chester on 7/10/2020.
 */

import java.io.Serializable;

/**
 * @ClassName CommentExample
 * @Description 评论查询条件的封装
 * @Author XiaoMao
 * @Date 7/10/2020 下午3:10
 * @Version 1.0
 **/

public class CommentExample implements Serializable {
    private String resName;
    private String userName;
    private String startTime;
    private String endTime;

    @Override
    public String toString() {
        return "CommentExample{" +
                "resName='" + resName + '\'' +
                ", userName='" + userName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
