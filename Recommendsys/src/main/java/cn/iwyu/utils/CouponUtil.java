package cn.iwyu.utils;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.service.CouponService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @ClassName CouponUtil
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午5:23
 * @Version 1.0
 **/
@Component
public class CouponUtil {
    @Resource
    CouponService couponService;

    public String getRandomNum(){
        int  maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer hash = new StringBuffer("");
        do {
            hash = new StringBuffer("");
            Random r = new Random();
            while(count < 8){
                i = Math.abs(r.nextInt(maxNum));
                if (i >= 0 && i < str.length) {
                    hash.append(str[i]);
                    count ++;
                }
            }
        }while (couponService.searchCode(hash.toString())!=0);
        return hash.toString();
    }
}
