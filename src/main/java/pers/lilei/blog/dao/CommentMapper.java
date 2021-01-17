package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.Comment;
import pers.lilei.blog.po.CommentExample;
import pers.lilei.blog.pojo.CommentWithUserBaseInfoPojo;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Long commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long commentId);

    Long selectUserIdByCommentId(Long commentId);

    List<CommentWithUserBaseInfoPojo> getRootCommentByArticleId(Long articleId);

    List<CommentWithUserBaseInfoPojo> getChildCommentByArticleIdAndParentCommentId(@Param("articleId") Long articleId, @Param("parentCommentId") Long parentCommentId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}
