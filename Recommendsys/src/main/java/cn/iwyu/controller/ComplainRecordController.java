package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.ComplainExample;
import cn.iwyu.domain.ComplainRecord;
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
    /**
    *@Description 后期处理登录的时候需要把登录的管理员ID从session域中取出来
    *@Author XiaoMao
    *@Date 7/10/2020 上午11:00
    *@Param
    *Return
    **/
    @RequestMapping("/pass")
    @ResponseBody
    public Msg pass(ComplainRecord complainRecord){
        Integer flag = service.update(complainRecord);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }

    @RequestMapping("/findByExample")
    @ResponseBody
    public Msg findByExample(ComplainExample example){
        List<ComplainRecordCustom> complainRecordCustoms = service.findByExample(example);
        if(complainRecordCustoms.size()>0){
            return Msg.succeed().add(complainRecordCustoms,complainRecordCustoms.size());
        }
        return Msg.fail();
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Msg batchDelete(List<ComplainRecord>complainRecords){
        Integer flag = service.batchDelete(complainRecords);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail();
    }
}
