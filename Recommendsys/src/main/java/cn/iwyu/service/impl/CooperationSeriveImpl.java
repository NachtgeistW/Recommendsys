package cn.iwyu.service.impl;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.dao.CooperationMapper;
import cn.iwyu.domain.Cooperation;
import cn.iwyu.service.CooperationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CooperationSeriveImpl
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午8:27
 * @Version 1.0
 **/
@Service
public class CooperationSeriveImpl implements CooperationService {

    @Resource
    private CooperationMapper cooperationMapper;

    @Override
    public int save(Cooperation cooperation) {
        return cooperationMapper.insert(cooperation);
    }

    @Override
    public List<Cooperation> findAll() {
        return cooperationMapper.selectAll();
    }

    @Override
    public int update(Cooperation cooperation) {
        return cooperationMapper.updateByPrimaryKey(cooperation);
    }

    @Override
    public int delete(Integer cooperationId) {
        return cooperationMapper.deleteByPrimaryKey(cooperationId);
    }
}
