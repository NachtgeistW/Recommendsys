package cn.iwyu.utils;/**
 * Created by Chester on 28/11/2020.
 */

/**
 * @ClassName UrlImgUtil
 * @Description
 * @Author XiaoMao
 * @Date 28/11/2020 下午6:35
 * @Version 1.0
 **/

public class UrlImgUtil {
    public static String change (String str){
//        String[] strs = str.split(",");
        String temp ="";
        for(int i =0;i<str.length();i++){
            char c = str.charAt(i);
            if(c==' '){
                temp = temp + "%20";
                continue;
            }
            temp = temp + c ;
        }
        return temp;
    }
}
