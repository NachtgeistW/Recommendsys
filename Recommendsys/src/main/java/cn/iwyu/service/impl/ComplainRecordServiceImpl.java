package cn.iwyu.service.impl;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.dao.ComplainRecordCustomMapper;
import cn.iwyu.dao.ComplainRecordMapper;
import cn.iwyu.domain.ComplainExample;
import cn.iwyu.domain.ComplainRecord;
import cn.iwyu.domain.ComplainRecordCustom;
import cn.iwyu.service.ComplainRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ComplainRecordServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午4:11
 * @Version 1.0
 **/
@Service
public class ComplainRecordServiceImpl implements ComplainRecordService{

    @Resource
    ComplainRecordMapper complainRecordMapper;
    @Resource
    ComplainRecordCustomMapper complainRecordCustomMapper;

    @Override
    public void save(ComplainRecord complainRecord) {
        complainRecordMapper.insert(complainRecord);
    }

    @Override
    public List<ComplainRecordCustom> findAll() {
        List<ComplainRecordCustom> complainRecordCustoms = complainRecordCustomMapper.findAll();
        for (ComplainRecordCustom c:complainRecordCustoms
             ) {
            c.setUserName(c.getUser().getUserName());
            c.setRestaurantName(c.getRestaurant().getName());
        }
        return complainRecordCustoms;
    }

    @Override
    public ComplainRecord findById(Integer complainId) {
        return complainRecordMapper.selectByPrimaryKey(complainId);
    }

    @Override
    public List<ComplainRecordCustom> findByUserId(Integer userId) {
        return complainRecordCustomMapper.findByUserId(userId);
    }

    @Override
    public List<ComplainRecordCustom> findByResId(Integer resId) {
        return complainRecordCustomMapper.findByResId(resId);
    }

    @Override
    public int update(ComplainRecord complainRecord) {
        return complainRecordMapper.updateByPrimaryKey(complainRecord);
    }

    @Override
    public int delete(Integer compId) {
        return complainRecordMapper.deleteByPrimaryKey(compId);
    }

    @Override
    public List<ComplainRecordCustom> checkRecord() {
        List<ComplainRecordCustom> complainRecordCustoms = complainRecordCustomMapper.checkRecord();
        for (ComplainRecordCustom c:complainRecordCustoms
        ) {
            c.setUserName(c.getUser().getUserName());
            c.setRestaurantName(c.getRestaurant().getName());
        }
        return complainRecordCustoms;
    }

    @Override
    public Integer batchDelete(List<Integer> ids) {
        Integer flag = 0;
        for (Integer id :ids) {
            flag += complainRecordMapper.deleteByPrimaryKey(id);
        }
        return flag;
    }

    @Override
    public List<ComplainRecordCustom> findByExample(ComplainExample example) {
        List<ComplainRecordCustom> complainRecordCustoms = complainRecordCustomMapper.findByExample(example);
        for (ComplainRecordCustom c:complainRecordCustoms
        ) {
            c.setUserName(c.getUser().getUserName());
            c.setRestaurantName(c.getRestaurant().getName());
        }
        return complainRecordCustoms;
    }
}
