package cn.iwyu.utils;/**
 * Created by Chester on 14/10/2020.
 */

import cn.iwyu.domain.CommentCustom;
import cn.iwyu.domain.Preference;
import cn.iwyu.domain.User;
import cn.iwyu.service.CommentService;
import cn.iwyu.service.PreferenceService;
import cn.iwyu.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PreferenceUtil
 * @Description 计算用户对某个餐馆的偏爱值:
 *              从评分中大于3分的属于好评，偏爱值加上评分值；评论为好评而且获赞>20额外加入两点的偏爱值
 * @Author XiaoMao
 * @Date 14/10/2020 下午8:23
 * @Version 1.0
 **/
@Component
public class PreferenceUtil {
    @Resource
    CommentService commentService;
    @Resource
    PreferenceService preferenceService;
    @Resource
    UserService userService;

    public Integer count(Integer userId){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Preference preference = new Preference();
//        Map<Integer,Double> score = new HashMap<>();
        Double temp = 0d;
        Integer resId = 0;
        List<CommentCustom> comments = commentService.findByUserId(userId);
        for (CommentCustom commentCustom :comments) {
            resId = commentCustom.getIdRestaurant();
            preference = preferenceService.searchUser(userId,resId);
            if(preference==null) {
                preference.setUserId(userId);
                preference.setDate(sdf.format(date));
            }
            temp = 0d;
            if(commentCustom.getScore()>3){
                temp = temp+commentCustom.getScore();
                if(commentCustom.getNumLike()>20){
                    temp = temp + 2;
                }
            }
            preference.setScore(temp);
        }
        return  preferenceService.save(preference);
    }
    /**
    *@Description 计算所有用户的偏好值
    *@Author XiaoMao
    *@Date 21/10/2020 下午3:49
    *@Param []
    *Return int
    **/
    public int countAll(){
        int temp = 0;
        List<User> users = userService.findAll();
        for (User user :users) {
            temp += count(user.getIdUser());
        }
        return temp;
    }
}

