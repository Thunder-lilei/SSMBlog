package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>草稿参数</p>
 *
 * @author : 李雷
 * @date : 2021-04-19 12:06
 **/
public class DraftParam {
    private Long draftId;

    private Long userId;

    private String articleTitle;

    public Long getDraftId() {
        return draftId;
    }

    public void setDraftId(Long draftId) {
        this.draftId = draftId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
}
