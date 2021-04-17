package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>博文基本信息</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 12:31
 **/
public class ArticleWithUserBaseInfoParam {
    private Long articleId;

    private String articleTitle;

    private Long articleCommentCount;

    private Long articleLikeCount;

    private Long articleViews;

    private Long userId;

    private UserBaseInfoParam userBaseInfoParam;

    public Long getArticleViews() {
        return articleViews;
    }

    public void setArticleViews(Long articleViews) {
        this.articleViews = articleViews;
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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Long getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Long articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Long getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Long articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

}
