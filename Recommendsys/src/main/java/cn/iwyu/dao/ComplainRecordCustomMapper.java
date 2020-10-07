package cn.iwyu.dao;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.ComplainExample;
import cn.iwyu.domain.ComplainRecordCustom;

import java.util.List;

/**
 * @InterfaceName ComplainRecordCustomMapper
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午3:57
 * @Version 1.0
 **/

public interface ComplainRecordCustomMapper {
    //查询所有举报信息，直接显示用户名和店铺名
    List<ComplainRecordCustom> findAll();
    //查询通过餐馆id查询举报信息，应该返回一个集合
    List<ComplainRecordCustom> findByResId(Integer resId);
    //通过用户id查询举报信息，应该返回一个集合
    List<ComplainRecordCustom> findByUserId(Integer userId);
    //通过条件模糊查询
    List<ComplainRecordCustom> findByExample(ComplainExample example);
}
