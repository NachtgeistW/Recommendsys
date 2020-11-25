package cn.iwyu.utils;/**
 * Created by Chester on 25/11/2020.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName StringToList
 * @Description
 * @Author XiaoMao
 * @Date 25/11/2020 下午5:32
 * @Version 1.0
 **/

public class StringToList {
    public static List<Integer> change(String ids) {
        List<Integer> list = new ArrayList<Integer>();
        if (ids != null && !(ids.equals(""))) {
            String[] idsStrings = ids.split(",");//转成String数组
            int[] array = Arrays.stream(idsStrings).mapToInt(Integer::parseInt).toArray();//转int数组
            list = Arrays.stream(array).boxed().collect(Collectors.toList());//转List<Integer>
            return list;
        }
        return null;
    }
}
