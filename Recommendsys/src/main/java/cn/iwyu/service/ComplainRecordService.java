package cn.iwyu.service;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.ComplainExample;
import cn.iwyu.domain.ComplainRecordCustom;
import cn.iwyu.domain.ComplainRecord;

import java.util.List;

/**
 * @InterfaceName ComplainRecordService
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午4:06
 * @Version 1.0
 **/

public interface ComplainRecordService {
    //添加举报信息
    int save(ComplainRecord complainRecord);
    //查询全部举报信息
    public List<ComplainRecordCustom> findAll();
    //通过id查询
    public ComplainRecord findById(Integer complainId);
    //通过用户id查询举报信息
    public List<ComplainRecordCustom> findByUserId(Integer userId);
    //通过餐馆id查询举报信息
    public List<ComplainRecordCustom> findByResId(Integer resId);
    //修改举报信息
    public int update(ComplainRecord complainRecord);
    //删除举报信息
    public int delete(Integer compId);
    //查询未处理的举报信息
    public List<ComplainRecordCustom> checkRecord();
    //通过条件查询举报信息
    public List<ComplainRecordCustom> findByExample(ComplainExample example);
    //批量删除举报信息
    public Integer batchDelete(List<Integer> ids);
}
