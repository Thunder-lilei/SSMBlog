package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.bean.ArticleLabel;
import pers.lilei.blog.bean.ArticleLabelExample;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.DraftParam;

public interface ArticleLabelMapper {
    long countByExample(ArticleLabelExample example);

    int deleteByExample(ArticleLabelExample example);

    int deleteByPrimaryKey(Long id);

    int deleteByArticleIdAndLabelId(@Param("articleId") Long articleId, @Param("labelId") Long labelId);

    int deleteByDraftIdIdAndLabelId(@Param("draftId") Long draftId, @Param("labelId") Long labelId);

    int deleteAllArticleLabel(@Param("param")ArticleParam articleParam);

    int deleteAllDraftLabel(@Param("param") DraftParam draftParam);

    int insert(ArticleLabel record);

    int insertSelective(ArticleLabel record);

    List<ArticleLabel> selectByExample(ArticleLabelExample example);

    ArticleLabel selectByPrimaryKey(Long id);

    ArticleLabel selectByArticleIdAndLabelId(@Param("articleId") Long articleId, @Param("labelId") Long labelId);

    ArticleLabel selectByDraftIdAndLabelId(@Param("draftId") Long draftId, @Param("labelId") Long labelId);

    int updateByExampleSelective(@Param("record") ArticleLabel record, @Param("example") ArticleLabelExample example);

    int updateByExample(@Param("record") ArticleLabel record, @Param("example") ArticleLabelExample example);

    int updateByPrimaryKeySelective(ArticleLabel record);

    int updateByPrimaryKey(ArticleLabel record);
}
