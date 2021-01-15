package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.Article;
import pers.lilei.blog.po.ArticleLike;
import pers.lilei.blog.po.ArticleLikeExample;

public interface ArticleLikeMapper {
    long countByExample(ArticleLikeExample example);

    int deleteByExample(ArticleLikeExample example);

    int deleteByPrimaryKey(Long id);

    int deleteByArticleIdAndUserId(@Param("articleId") Long articleId, @Param("userId") Long userId);

    int insert(ArticleLike record);

    int insertSelective(ArticleLike record);

    List<ArticleLike> selectByExample(ArticleLikeExample example);

    ArticleLike selectByPrimaryKey(Long id);

    ArticleLike selectByArticleIdAndUserId(@Param("articleId") Long articleId, @Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") ArticleLike record, @Param("example") ArticleLikeExample example);

    int updateByExample(@Param("record") ArticleLike record, @Param("example") ArticleLikeExample example);

    int updateByPrimaryKeySelective(ArticleLike record);

    int updateByPrimaryKey(ArticleLike record);
}
