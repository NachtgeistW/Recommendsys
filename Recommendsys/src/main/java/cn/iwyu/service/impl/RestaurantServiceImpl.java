package cn.iwyu.service.impl;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.dao.CommentCustomMapper;
import cn.iwyu.dao.RestaurantCustomMapper;
import cn.iwyu.dao.RestaurantMapper;
import cn.iwyu.domain.RecommendRes;
import cn.iwyu.domain.Restaurant;
import cn.iwyu.domain.RestaurantCustom;
import cn.iwyu.domain.RestaurantExample;
import cn.iwyu.service.RestaurantService;
import cn.iwyu.utils.RecommendResUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RestaurantServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午5:30
 * @Version 1.0
 **/
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Resource
    RestaurantMapper restaurantMapper;
    @Resource
    RestaurantCustomMapper customMapper;
    @Resource
    CommentCustomMapper commentCustomMapper;

    @Transactional
    @Override
    public Integer save(Restaurant restaurant) {
        return customMapper.saveGetId(restaurant);
    }

    @Override
    public Restaurant findById(Integer resId) {
        return restaurantMapper.selectByPrimaryKey(resId);
    }

    @Override
    public List<RestaurantCustom> findAll() {
        List<RestaurantCustom> restaurantCustoms = customMapper.findAllRecommender();
        for (RestaurantCustom restaurantCustom:restaurantCustoms
        ) {
            restaurantCustom.setUserName(restaurantCustom.getUser().getUserName());
        }
        return restaurantCustoms;
    }

    @Override
    public int update(Restaurant restaurant) {
        return restaurantMapper.updateByPrimaryKey(restaurant);
    }

    @Override
    public int delete(Integer resId) {
        return restaurantMapper.deleteByPrimaryKey(resId);
    }

    @Override
    public List<RestaurantCustom> checkRecommend() {
        return customMapper.checkRecommend();
    }

    @Override
    public List<RestaurantCustom> findByExample(RestaurantExample example) {
        List<RestaurantCustom> restaurantCustoms = customMapper.findByExample(example);
        for (RestaurantCustom restaurantCustom:restaurantCustoms
        ) {
            restaurantCustom.setUserName(restaurantCustom.getUser().getUserName());
        }
        return restaurantCustoms;
    }

    @Override
    public Integer batchDelete(List<Integer> ids) {
        Integer flag = 0;
        for (Integer id :ids) {
            flag += restaurantMapper.deleteByPrimaryKey(id);
        }
        return flag;
    }

    @Override
    public Integer updateImg(Restaurant restaurant) {
        return customMapper.updateImg(restaurant);
    }

    @Override
    public List<RecommendRes> passRecommend(int num) {
        List<RestaurantCustom> restaurantCustoms = customMapper.passRecommend();
        List<RecommendRes> recommendRes = new ArrayList<>();
        int n = restaurantCustoms.size()>num ? num:restaurantCustoms.size();
        for (int i =0;i<n;i++){
            RestaurantCustom restaurantCustom = restaurantCustoms.get(i);
            RecommendRes res = RecommendResUtil.change(restaurantCustom);
            res.setComment_num(commentCustomMapper.findByResId(restaurantCustom.getIdRestaurant()).size());
            recommendRes.add(res);
        }
        return recommendRes;
    }
}
