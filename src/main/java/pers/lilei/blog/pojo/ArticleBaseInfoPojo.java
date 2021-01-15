package pers.lilei.blog.pojo;

/**
 * <h3>SSMBlog</h3>
 * <p>博文基本信息</p>
 *
 * @author : 李雷
 * @date : 2021-01-15 22:31
 **/
public class ArticleBaseInfoPojo {
    private Long articleId;

    private String articleTitle;

    private Long articleCommentCount;

    private Long articleLikeCount;

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
