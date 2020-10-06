package cn.iwyu.dao;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.RestaurantCustom;
import cn.iwyu.domain.RestaurantExample;

import java.util.List;

/**
 * @InterfaceName RestaurantCustomMapper
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午5:20
 * @Version 1.0
 **/

public interface RestaurantCustomMapper {
    public List<RestaurantCustom> findAllRecommender();
    //查询为审核的店铺
    public List<RestaurantCustom> checkRecommend();
    //通过条件查询
    public List<RestaurantCustom> findByExample(RestaurantExample example);
}
