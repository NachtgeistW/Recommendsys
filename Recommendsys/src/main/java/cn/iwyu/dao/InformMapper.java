package cn.iwyu.dao;

import cn.iwyu.domain.Inform;
import java.util.List;

public interface InformMapper {
    int deleteByPrimaryKey(Integer informId);

    int insert(Inform record);

    Inform selectByPrimaryKey(Integer informId);

    List<Inform> selectAll();

    int updateByPrimaryKey(Inform record);
}