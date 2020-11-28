package cn.iwyu.utils;/**
 * Created by Chester on 28/11/2020.
 */

import cn.iwyu.domain.RecommendRes;
import cn.iwyu.domain.Restaurant;
import cn.iwyu.domain.RestaurantCustom;

import java.util.List;

/**
 * @ClassName RecommendResUtil
 * @Description
 * @Author XiaoMao
 * @Date 28/11/2020 下午2:50
 * @Version 1.0
 **/

public class RecommendResUtil {
    public static RecommendRes change (Restaurant restaurant){
        RecommendRes recommendRes = new RecommendRes();
        recommendRes.setComment(restaurant.getComment());
        recommendRes.setAddress(restaurant.getAddress());
        recommendRes.setIdRecommandedUser(restaurant.getIdRecommandedUser());
        recommendRes.setIntro(restaurant.getIntro());
        recommendRes.setName(restaurant.getName());
        recommendRes.setRecommandReason(restaurant.getRecommandReason());
        recommendRes.setTypeOfCuisine(restaurant.getTypeOfCuisine());
        if(restaurant.getResturantImage()!=null){
            String[] strs = restaurant.getResturantImage().split(",");
            String str ="";
            for(int i =0;i<strs[0].length();i++){
                char c = strs[0].charAt(i);
                if(c==' '){
                    str = str + "%20";
                    continue;
                }
                str = str + c ;
            }
            strs[0] = str;
            recommendRes.setResturantImage(strs[0]);
        }
        recommendRes.setRecommendTime(restaurant.getRecommendTime());
        recommendRes.setIdRestaurant(restaurant.getIdRestaurant());
        return recommendRes;
    }
}
