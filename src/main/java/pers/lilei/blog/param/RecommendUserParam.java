package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>推荐用户基本信息</p>
 *
 * @author : 李雷
 * @date : 2021-01-22 22:27
 **/
public class RecommendUserParam {
    private Long sumCommentCount;

    private Long sumLikeCount;

    private Long sumViews;

    private Long userId;

    private UserBaseInfoParam userBaseInfoParam;

    public Long getSumCommentCount() {
        return sumCommentCount;
    }

    public void setSumCommentCount(Long sumCommentCount) {
        this.sumCommentCount = sumCommentCount;
    }

    public Long getSumLikeCount() {
        return sumLikeCount;
    }

    public void setSumLikeCount(Long sumLikeCount) {
        this.sumLikeCount = sumLikeCount;
    }

    public Long getSumViews() {
        return sumViews;
    }

    public void setSumViews(Long sumViews) {
        this.sumViews = sumViews;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserBaseInfoParam getUserBaseInfoPojo() {
        return userBaseInfoParam;
    }

    public void setUserBaseInfoPojo(UserBaseInfoParam userBaseInfoParam) {
        this.userBaseInfoParam = userBaseInfoParam;
    }
}
