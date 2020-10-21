package cn.iwyu.service;/**
 * Created by Chester on 21/10/2020.
 */


import cn.iwyu.domain.Preference;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 * @ClassName PreferenceService
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午3:14
 * @Version 1.0
 **/

public interface PreferenceService {
    //写入每个用户对不同店铺的偏好值
    public int save(Preference preference);
    //查询用户是否已经对某个店铺计算过偏好值
    public Preference searchUser(Integer userId,Integer resId);
    //推荐店铺
    int getResByPre() throws TasteException;
}
