package pers.lilei.blog.pojo;

/**
 * <h3>SSMBlog</h3>
 * <p>博文基本信息</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 12:31
 **/
public class ArticleWithUserBaseInfoPojo {
    private Long articleId;

    private String articleTitle;

    private Long articleCommentCount;

    private Long articleLikeCount;

    private Long articleViews;

    private Long userId;

    private UserBaseInfoPojo userBaseInfoPojo;

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

    public UserBaseInfoPojo getUserBaseInfoPojo() {
        return userBaseInfoPojo;
    }

    public void setUserBaseInfoPojo(UserBaseInfoPojo userBaseInfoPojo) {
        this.userBaseInfoPojo = userBaseInfoPojo;
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
