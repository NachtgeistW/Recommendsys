package cn.iwyu.domain;/**
 * Created by Chester on 7/10/2020.
 */

import java.io.Serializable;

/**
 * @ClassName ComplainExample
 * @Description
 * @Author XiaoMao
 * @Date 7/10/2020 上午11:05
 * @Version 1.0
 **/

public class ComplainExample implements Serializable {
    private String userName;
    private String resName;
    private String result;

    @Override
    public String toString() {
        return "ComplainExample{" +
                "userName='" + userName + '\'' +
                ", resName='" + resName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
