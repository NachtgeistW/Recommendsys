package cn.iwyu.dao;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.domain.Preference;

/**
 * @InterfaceName PreferenceCustomMapper
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午3:34
 * @Version 1.0
 **/

public interface PreferenceCustomMapper {
    //通过用户id和店铺id查询偏好值
    Preference findByUserIdAndResId(Integer userId,Integer resId);
}
