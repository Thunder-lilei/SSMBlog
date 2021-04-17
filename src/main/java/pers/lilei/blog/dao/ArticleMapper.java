package pers.lilei.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.bean.Article;
import pers.lilei.blog.bean.ArticleExample;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.param.ArticleBaseInfoParam;
import pers.lilei.blog.param.ArticleWithUserBaseInfoParam;
import pers.lilei.blog.param.RecommendUserParam;

public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    List<ArticleWithBLOBs> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    ArticleWithBLOBs selectByPrimaryKey(Long articleId);

    ArticleWithBLOBs selectByArticleTitle(String title);

    List<ArticleWithUserBaseInfoParam> selectAllArticleWithUserBaseInfoByUserId(Long userId);

    List<ArticleBaseInfoParam> selectSortAboutArticleWithUserId(@Param("sortId") Long sortId, @Param("userId") Long userId);

    List<ArticleBaseInfoParam> selectLabelAboutArticleWithUserId(@Param("labelId") Long labelId, @Param("userId") Long userId);

    List<ArticleBaseInfoParam> selectSortAboutArticleWithUserIdAndKey(@Param("sortId") Long sortId, @Param("userId") Long userId, @Param("key") String key);

    List<ArticleBaseInfoParam> selectLabelAboutArticleWithUserIdAndKey(@Param("labelId") Long labelId, @Param("userId") Long userId, @Param("key") String key);

    List<ArticleWithUserBaseInfoParam> selectArticleWithUserBaseInfoByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    List<ArticleBaseInfoParam> selectAllArticleBaseInfoByUserId(Long userId);

    List<ArticleWithUserBaseInfoParam> selectArticleByKey(String key);

    List<ArticleBaseInfoParam> selectArticleBaseInfoByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    List<ArticleWithUserBaseInfoParam> getRecommendArticle(int size);

    List<RecommendUserParam> getRecommendUser(int size);

    Long selectUserIdByArticleId(Long articleId);

    int updateByExampleSelective(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}
