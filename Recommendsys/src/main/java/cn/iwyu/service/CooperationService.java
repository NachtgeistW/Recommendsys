package cn.iwyu.service;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.domain.Cooperation;

import java.util.List;

/**
 * @InterfaceName CooperationService
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午8:22
 * @Version 1.0
 **/

public interface CooperationService {
    //添加商业合作信息
    int save(Cooperation cooperation);
    //获取所有的合作信息
    List<Cooperation> findAll();
    //修改合作信息
    int update(Cooperation cooperation);
    //删除错误的合作信息
    int delete(Integer cooperationId);
    //获取某间店铺的合作信息（暂时不实现）
}
