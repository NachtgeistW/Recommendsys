package cn.iwyu.domain;/**
 * Created by Chester on 28/11/2020.
 */

/**
 * @ClassName RecommendRes
 * @Description
 * @Author XiaoMao
 * @Date 28/11/2020 下午2:17
 * @Version 1.0
 **/

public class RecommendRes extends Restaurant{
    private Integer comment_num;

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }
}
