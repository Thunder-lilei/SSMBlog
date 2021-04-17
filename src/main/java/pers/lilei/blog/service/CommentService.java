package pers.lilei.blog.service;

import pers.lilei.blog.bean.Comment;
import pers.lilei.blog.param.CommentParam;
import pers.lilei.blog.param.CommentWithUserBaseInfoParam;

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

    Integer deleteComment(CommentParam commentParam);

    List<CommentWithUserBaseInfoParam> getCommentByArticleId(Long articleId);

    List<CommentWithUserBaseInfoParam> getRootCommentByArticleId(Long articleId);

    List<CommentWithUserBaseInfoParam> getChildCommentByArticleIdAndParentCommentId(List<CommentWithUserBaseInfoParam> parentCommentWithUserBaseInfoParamList, Long articleId, Long parentCommentId);
}
