package cn.iwyu.service.impl;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.dao.CouponCustomMapper;
import cn.iwyu.dao.CouponMapper;
import cn.iwyu.domain.Coupon;
import cn.iwyu.service.CouponService;
import cn.iwyu.utils.CouponUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CouponServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午5:10
 * @Version 1.0
 **/
@Service
public class CouponServiceImpl implements CouponService {
    @Resource
    private CouponUtil couponUtil;
    @Resource
    private CouponMapper mapper;
    @Resource
    private CouponCustomMapper couponCustomMapper;

    @Override
    public int searchCode(String code) {
        Coupon coupon = couponCustomMapper.searchCode(code);
        if(coupon==null){
            return 1;
        }
        return 0;
    }

    @Override
    public int save(Coupon coupon) {
        coupon.setAllocation(1);
        String code = couponUtil.getRandomNum();
        coupon.setHashId(code);
        mapper.insert(coupon);
        return 0;
    }

    @Override
    public int saveSome(Coupon coupon, int count) {
        for(int i=0;i<count;i++){
            coupon.setAllocation(1);
            String code = couponUtil.getRandomNum();
            coupon.setHashId(code);
            mapper.insert(coupon);
        }
        return 0;
    }
/**
*@Description 使状态未0的优惠券改为1不等于3
*@Author XiaoMao
*@Date 21/10/2020 下午7:31
*@Param [couponId]
*Return int
**/
    @Override
    public int change(Integer couponId) {
        Coupon coupon = mapper.selectByPrimaryKey(couponId);
        if(coupon.getAllocation()!=0){
            coupon.setAllocation(2);
            return mapper.updateByPrimaryKey(coupon);
        }
        return 0;
    }

    @Override
    public int giveCoupon(Integer couponId, Integer userId) {
        return 0;
    }

    @Override
    public int useCoupon(String code) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Coupon coupon = couponCustomMapper.searchCode(code);
        if(coupon.getAllocation()==3){
            return -1;
        }
        if(coupon.getUserId()!=null&&coupon.getAllocation()==2){
            coupon.setUseDate(sdf.format(date));
            coupon.setAllocation(3);
            return mapper.updateByPrimaryKey(coupon);
        }
        return 0;
    }

    @Override
    public List<Coupon> findByUser(Integer userId) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Coupon> overdue = couponCustomMapper.getOverdue(userId,sdf.format(date));
        for (Coupon coupon :overdue) {
            coupon.setAllocation(0);
            mapper.updateByPrimaryKey(coupon);
        }
        return couponCustomMapper.searchByUser(userId);
    }
}
