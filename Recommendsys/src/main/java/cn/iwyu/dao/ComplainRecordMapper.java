package cn.iwyu.dao;

import cn.iwyu.domain.ComplainRecord;
import cn.iwyu.domain.ComplainRecordCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComplainRecordMapper {
    int deleteByPrimaryKey(Integer idComplainRecord);

    int insert(ComplainRecord record);

    ComplainRecord selectByPrimaryKey(Integer idComplainRecord);

    List<ComplainRecord> selectAll();

    int updateByPrimaryKey(ComplainRecord record);
    //查询未处理的举报信息
    public List<ComplainRecordCustom> checkRecord();
}