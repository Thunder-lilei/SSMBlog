package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.bean.Comment;
import pers.lilei.blog.bean.CommentExample;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.CommentParam;
import pers.lilei.blog.param.CommentWithUserBaseInfoParam;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(@Param("param") CommentParam commentParam);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long commentId);

    Long selectUserIdByCommentId(Long commentId);

    List<CommentWithUserBaseInfoParam> getRootCommentByArticleId(Long articleId);

    List<CommentWithUserBaseInfoParam> getChildCommentByArticleIdAndParentCommentId(@Param("articleId") Long articleId, @Param("parentCommentId") Long parentCommentId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    int selectArticleCommentNum(@Param("param")ArticleParam articleParam);

    Long getArticleIdByCommentId(@Param("param")CommentParam commentParam);
}
