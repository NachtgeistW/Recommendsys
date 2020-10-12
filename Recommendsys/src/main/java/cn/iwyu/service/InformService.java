package cn.iwyu.service;/**
 * Created by Chester on 12/10/2020.
 */

import cn.iwyu.domain.Inform;

import java.util.List;

/**
 * @InterfaceName InformService
 * @Description
 * @Author XiaoMao
 * @Date 12/10/2020 下午3:02
 * @Version 1.0
 **/

public interface InformService {
    public Integer sendInform(Inform inform);
    //获取某用户所有消息
    public List<Inform> userFindAll(Integer userId);
    //一键阅读所有未读消息
    public Integer readAll(Integer userId);
    //删除某条消息
    public Integer delete(Integer informId);

}
