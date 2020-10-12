package cn.iwyu.dao;

import cn.iwyu.domain.Cooperation;
import java.util.List;

public interface CooperationMapper {
    int deleteByPrimaryKey(Integer cooperationId);

    int insert(Cooperation record);

    Cooperation selectByPrimaryKey(Integer cooperationId);

    List<Cooperation> selectAll();

    int updateByPrimaryKey(Cooperation record);
}