package cn.iwyu.service.impl;/**
 * Created by Chester on 12/10/2020.
 */

import cn.iwyu.dao.InformCustomMapper;
import cn.iwyu.dao.InformMapper;
import cn.iwyu.domain.Inform;
import cn.iwyu.service.InformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName InformServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 12/10/2020 下午3:04
 * @Version 1.0
 **/
@Service
public class InformServiceImpl implements InformService {

    @Resource
    private InformMapper mapper;
    @Resource
    private InformCustomMapper customMapper;

    @Override
    public Integer sendInform(Inform inform) {
        return mapper.insert(inform);
    }

    @Override
    public List<Inform> userFindAll(Integer userId) {
        return customMapper.findAll(userId);
    }

    @Override
    public Integer readAll(Integer userId) {
        Integer temp = 0;
        List<Inform> informList = customMapper.readAll(userId);
        for (Inform inform :informList) {
            temp = temp+customMapper.read(inform);
        }
        return temp;
    }

    @Override
    public Integer delete(Integer informId) {
        return mapper.deleteByPrimaryKey(informId);
    }
}
