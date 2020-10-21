package cn.iwyu.service.impl;/**
 * Created by Chester on 21/10/2020.
 */

import cn.iwyu.dao.PreferenceCustomMapper;
import cn.iwyu.dao.PreferenceMapper;
import cn.iwyu.domain.Preference;
import cn.iwyu.service.PreferenceService;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName PreferenceServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 21/10/2020 下午3:23
 * @Version 1.0
 **/
@Service
public class PreferenceServiceImpl implements PreferenceService {
    @Resource
    PreferenceMapper mapper;
    @Resource
    PreferenceCustomMapper customMapper;
    @Resource
    DataSource dataSource;

    @Override
    public int save(Preference preference) {
        return mapper.insert(preference);
    }

    @Override
    public Preference searchUser(Integer userId, Integer resId) {
        return customMapper.findByUserIdAndResId(userId,resId);
    }

    @Override
    public int getResByPre() throws TasteException {
        DataModel dataModel = new MySQLJDBCDataModel(dataSource,"preference","user_id","restaurant_id","score","date");
        //根据模型获取userId迭代器
        LongPrimitiveIterator iter = dataModel.getUserIDs();
        //欧几里得相似度
        UserSimilarity user = new EuclideanDistanceSimilarity(dataModel);
        //2代表--限制在模型中的用户数量
        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(2, user, dataModel);
        Recommender r = new GenericUserBasedRecommender(dataModel, neighbor, user);
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            //3代表--所需要的行为数
            List<RecommendedItem> list = r.recommend(uid, 3);
            System.out.printf("uid:%s", uid);
            for (RecommendedItem ritem : list) {
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
            }
            System.out.println();
        }
        return 0;
    }
}
