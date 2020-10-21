package cn.iwyu.dao;

import cn.iwyu.domain.Preference;
import java.util.List;

public interface PreferenceMapper {
    int deleteByPrimaryKey(Integer preferenceId);

    int insert(Preference record);

    Preference selectByPrimaryKey(Integer preferenceId);

    List<Preference> selectAll();

    int updateByPrimaryKey(Preference record);
}