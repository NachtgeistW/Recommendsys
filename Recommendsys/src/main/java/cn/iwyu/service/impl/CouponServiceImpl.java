package cn.iwyu.service.impl;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.dao.CouponCustomMapper;
import cn.iwyu.dao.CouponMapper;
import cn.iwyu.domain.Coupon;
import cn.iwyu.service.CouponService;
import cn.iwyu.utils.CouponUtil;

import javax.annotation.Resource;

/**
 * @ClassName CouponServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午5:10
 * @Version 1.0
 **/

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
        coupon.setAllocation(0);
        String code = couponUtil.getRandomNum();
        coupon.setHashId(code);
        return 0;
    }

    @Override
    public int saveSome(Coupon coupon, int count) {
        return 0;
    }
}
