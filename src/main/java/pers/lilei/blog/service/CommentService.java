package pers.lilei.blog.service;

import pers.lilei.blog.po.Comment;
import pers.lilei.blog.pojo.CommentWithUserBaseInfoPojo;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>评论服务层</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:16
 **/
public interface CommentService {
    Integer addComment(Comment comment);

    Integer deleteComment(Long commentId);

    List<CommentWithUserBaseInfoPojo> getCommentByArticleId(Long articleId);

    List<CommentWithUserBaseInfoPojo> getRootCommentByArticleId(Long articleId);

    List<CommentWithUserBaseInfoPojo> getChildCommentByArticleIdAndParentCommentId(List<CommentWithUserBaseInfoPojo> parentCommentWithUserBaseInfoPojoList, Long articleId, Long parentCommentId);
}
