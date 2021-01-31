package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.ArticleLabel;
import pers.lilei.blog.po.ArticleLabelExample;
import pers.lilei.blog.po.Label;

public interface ArticleLabelMapper {
    long countByExample(ArticleLabelExample example);

    int deleteByExample(ArticleLabelExample example);

    int deleteByPrimaryKey(Long id);

    int deleteByArticleIdAndLabelId(@Param("articleId") Long articleId, @Param("labelId") Long labelId);

    int insert(ArticleLabel record);

    int insertSelective(ArticleLabel record);

    List<ArticleLabel> selectByExample(ArticleLabelExample example);

    ArticleLabel selectByPrimaryKey(Long id);

    ArticleLabel selectByArticleIdAndLabelId(@Param("articleId") Long articleId, @Param("labelId") Long labelId);

    int updateByExampleSelective(@Param("record") ArticleLabel record, @Param("example") ArticleLabelExample example);

    int updateByExample(@Param("record") ArticleLabel record, @Param("example") ArticleLabelExample example);

    int updateByPrimaryKeySelective(ArticleLabel record);

    int updateByPrimaryKey(ArticleLabel record);
}
