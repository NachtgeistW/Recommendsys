package cn.iwyu.service;/**
 * Created by Chester on 29/9/2020.
 */

import cn.iwyu.domain.Restaurant;
import cn.iwyu.domain.RestaurantCustom;
import cn.iwyu.domain.RestaurantExample;

import java.util.List;

/**
 * @InterfaceName RestaurantService
 * @Description
 * @Author XiaoMao
 * @Date 29/9/2020 下午11:51
 * @Version 1.0
 **/

public interface RestaurantService {
    //添加餐馆
    public Integer save(Restaurant restaurant);
    //查询餐馆
    public Restaurant findById(Integer resId);
    //查询所有餐馆
    public List<RestaurantCustom> findAll();
    //更新餐馆
    public int update(Restaurant restaurant);
    //删除餐馆
    public int delete(Integer resId);
    //查询为审核的店铺
    public List<RestaurantCustom> checkRecommend();
    //通过条件查询
    public List<RestaurantCustom> findByExample(RestaurantExample example);
    //批量删除餐馆
    public Integer batchDelete(List<Restaurant> restaurants);
}
