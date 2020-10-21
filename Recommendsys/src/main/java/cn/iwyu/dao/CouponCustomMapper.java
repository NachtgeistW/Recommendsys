package cn.iwyu.dao;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.domain.Coupon;

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
}
