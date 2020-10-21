package cn.iwyu.dao;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.domain.Coupon;

import java.util.List;

/**
 * @InterfaceName CouponCustomMapper
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午5:27
 * @Version 1.0
 **/

public interface CouponCustomMapper {
    //查询优惠券代码是否已经存在
    Coupon searchCode(String code);
    //通过用户id查询其拥有的优惠券
    List<Coupon> searchByUser(Integer userId);
    //获取用户未使用的过期优惠券，即状态为1和2
    List<Coupon> getOverdue(Integer userId,String date);
}
