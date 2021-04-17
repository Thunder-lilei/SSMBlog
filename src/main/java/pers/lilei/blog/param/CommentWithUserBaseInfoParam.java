package pers.lilei.blog.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <h3>SSMBlog</h3>
 * <p>评论包含用户基本信息</p>
 *
 * @author : 李雷
 * @date : 2021-01-17 19:48
 **/
public class CommentWithUserBaseInfoParam {
    private Long commentId;

    private Long userId;

    private UserBaseInfoParam userBaseInfo;

    private Long articleId;

    private Long commentLikeCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date commentDate;

    private Long parentCommentId;

    private UserBaseInfoParam parentCommentUserBaseInfo;

    private String commentContent;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCommentLikeCount() {
        return commentLikeCount;
    }

    public void setCommentLikeCount(Long commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserBaseInfoParam getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfoParam userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public UserBaseInfoParam getParentCommentUserBaseInfo() {
        return parentCommentUserBaseInfo;
    }

    public void setParentCommentUserBaseInfo(UserBaseInfoParam parentCommentUserBaseInfo) {
        this.parentCommentUserBaseInfo = parentCommentUserBaseInfo;
    }
}
