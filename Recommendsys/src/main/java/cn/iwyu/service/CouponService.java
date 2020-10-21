package cn.iwyu.service;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.domain.Coupon;
import cn.iwyu.domain.Preference;

import java.util.List;

/**
 * @InterfaceName CouponService
 * @Description 优惠券服务层
 * @Author XiaoMao
 * @Date 21/10/2020 下午5:05
 * @Version 1.0
 **/

public interface CouponService {
    //添加一张优惠券
    public int save(Coupon coupon);
    //添加一定数量相同的优惠券
    public int saveSome(Coupon coupon,int count);
    //查询优惠券代码是否已经存在
    public int searchCode(String code);
    //使优惠券可用
    public int change(Integer couponId);
    //分配优惠券给某个用户
    public int giveCoupon(Integer couponId,Integer userId);
    //使用兑换优惠券（使该优惠券失效）
    public int useCoupon(String code);
    //查询用户拥有的优惠券（顺便查询已经过期的优惠券，将其标记为失效的0状态）
    public List<Coupon> findByUser(Integer userId);
}
