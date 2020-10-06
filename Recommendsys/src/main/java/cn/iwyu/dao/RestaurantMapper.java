package cn.iwyu.dao;

import cn.iwyu.domain.Restaurant;
import java.util.List;

public interface RestaurantMapper {
    int deleteByPrimaryKey(Integer idRestaurant);

    int insert(Restaurant record);

    Restaurant selectByPrimaryKey(Integer idRestaurant);

    List<Restaurant> selectAll();

    int updateByPrimaryKey(Restaurant record);
}