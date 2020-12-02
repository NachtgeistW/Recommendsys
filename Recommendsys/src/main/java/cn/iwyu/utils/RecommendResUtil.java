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
            String[] strs =restaurant.getResturantImage().split(",");
            String str = UrlImgUtil.change(strs[0]);
            recommendRes.setResturantImage(str);
        }
        recommendRes.setRecommendTime(restaurant.getRecommendTime());
        recommendRes.setIdRestaurant(restaurant.getIdRestaurant());
        return recommendRes;
    }
}
