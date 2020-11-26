package cn.iwyu.controller;/**
 * Created by Chester on 3/10/2020.
 */

import cn.iwyu.domain.ComplainExample;
import cn.iwyu.domain.ComplainRecord;
import cn.iwyu.domain.ComplainRecordCustom;
import cn.iwyu.domain.Msg;
import cn.iwyu.service.ComplainRecordService;
import cn.iwyu.utils.StringToList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ComplainRecordController
 * @Description
 * @Author XiaoMao
 * @Date 3/10/2020 下午12:17
 * @Version 1.0
 **/
@Controller
@RequestMapping("/Complain")
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
        return Msg.fail("数据库中暂无数据");
    }
    /**
    *@Description 查询所有未处理的举报结果
    *@Author XiaoMao
    *@Date 10/10/2020 下午4:08
    *@Param []
    *Return cn.iwyu.domain.Msg
    **/
    @RequestMapping("/checkRecord")
    @ResponseBody
    public Msg checkRecord(){
        List<ComplainRecordCustom> complainRecordCustoms = service.checkRecord();
        if(complainRecordCustoms.size()>0){
            return Msg.succeed().add(complainRecordCustoms,complainRecordCustoms.size());
        }
        return Msg.fail("数据库中暂无举报信息");
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
    public Msg pass(Integer idComplainRecord,String record_result, HttpSession session){
//        System.out.println(idComplainRecord);
        ComplainRecord complainRecord = service.findById(idComplainRecord);
        if(complainRecord==null){
            return Msg.fail("没有查询到数据");
        }

        complainRecord.setIdAdmin((Integer) session.getAttribute("userID"));
//        complainRecord.setIdAdmin(1);
        complainRecord.setIsProcessed((byte)1);
        complainRecord.setResult(record_result);
        Integer flag = service.update(complainRecord);
        if(flag>0){
            return Msg.succeed();
        }
        return Msg.fail("审核通过失败");
    }

    @RequestMapping("/findByExample")
    @ResponseBody
    public Msg findByExample(ComplainExample example){
        List<ComplainRecordCustom> complainRecordCustoms = service.findByExample(example);
        if(complainRecordCustoms.size()>0){
            return Msg.succeed().add(complainRecordCustoms,complainRecordCustoms.size());
        }
        return Msg.fail("没有找到拥有该条件的数据");
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Msg delete(Integer id){
        if(service.delete(id)!=0){
            return Msg.succeed("删除成功");
        }
        return Msg.fail("删除失败");
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Msg batchDelete(String ids){
        List<Integer> list = StringToList.change(ids);
        if(list!=null){
            Integer flag = service.batchDelete(list);
            if(flag>0){
                return Msg.succeed("删除成功");
            }
        }
        return Msg.fail("批量删除失败");
    }
}
