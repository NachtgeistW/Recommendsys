package cn.iwyu.dao;/**
 * Created by Chester on 12/10/2020.
 */

import cn.iwyu.domain.Inform;

import java.util.List;

/**
 * @InterfaceName InformCustomMapper
 * @Description
 * @Author XiaoMao
 * @Date 12/10/2020 下午3:55
 * @Version 1.0
 **/

public interface InformCustomMapper {
    //阅读消息
    Integer read(Inform inform);
    //获取某位用户的所有消息
    List<Inform> findAll(Integer userId);
    //获取某位用户的所有未读消息
    List<Inform> readAll(Integer userId);
}
