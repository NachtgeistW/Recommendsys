package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.ComplainRecordCustom;
import cn.iwyu.domain.Msg;
import cn.iwyu.service.ComplainRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ComplainRecordController
 * @Description
 * @Author XiaoMao
 * @Date 3/10/2020 下午12:17
 * @Version 1.0
 **/
@Controller
@RequestMapping("complain")
public class ComplainRecordController {
    @Resource
    private ComplainRecordService service;

    @RequestMapping("/findAll")
    @ResponseBody
    public Msg findAll(){
        List<ComplainRecordCustom> complainRecordCustoms = service.findAll();
        if(complainRecordCustoms.size()>0){
            return Msg.succeed().add(complainRecordCustoms,complainRecordCustoms.size());
        }
        return Msg.fail();
    }
    @RequestMapping("/checkRecord")
    @ResponseBody
    public Msg checkRecord(){
        List<ComplainRecordCustom> complainRecordCustoms = service.checkRecord();
        if(complainRecordCustoms.size()>0){
            return Msg.succeed().add(complainRecordCustoms,complainRecordCustoms.size());
        }
        return Msg.fail();
    }
}
