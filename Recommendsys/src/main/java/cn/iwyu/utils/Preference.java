package cn.iwyu.utils;/**
 * Created by Chester on 14/10/2020.
 */

import cn.iwyu.domain.Comment;
import cn.iwyu.domain.CommentCustom;
import cn.iwyu.service.CommentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Preference
 * @Description 计算用户对某个餐馆的偏爱值:
 *              从评分中大于3分的属于好评，偏爱值加上评分值；评论为好评而且获赞>20额外加入两点的偏爱值
 * @Author XiaoMao
 * @Date 14/10/2020 下午8:23
 * @Version 1.0
 **/
@Component
public class Preference {
    @Resource
    CommentService commentService;

    public double count(Integer userId){
        Map<Integer,Double> score = new HashMap<>();
        Double temp = 0d;
        Integer resId = 0;
        List<CommentCustom> comments = commentService.findByUserId(userId);
        for (CommentCustom commentCustom :comments) {
            temp = 0d;
            if(commentCustom.getScore()>3){
                temp = temp+commentCustom.getScore();
                if(commentCustom.getNumLike()>20){
                    temp = temp + 2;
                }
            }
            resId = commentCustom.getIdRestaurant();
            score.put(resId,temp);
        }
        return  0;
    }
}
