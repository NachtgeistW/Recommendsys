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
    private String usesrName;
    private String resName;
    private String result;

    public String getUsesrName() {
        return usesrName;
    }

    public void setUsesrName(String usesrName) {
        this.usesrName = usesrName;
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
