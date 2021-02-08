package pers.lilei.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.Article;
import pers.lilei.blog.po.ArticleExample;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.pojo.ArticleBaseInfoPojo;
import pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo;
import pers.lilei.blog.pojo.RecommendUserPojo;

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

    List<ArticleWithUserBaseInfoPojo> selectAllArticleWithUserBaseInfoByUserId(Long userId);

    List<ArticleBaseInfoPojo> selectSortAboutArticleWithUserId(@Param("sortId") Long sortId, @Param("userId") Long userId);

    List<ArticleBaseInfoPojo> selectLabelAboutArticleWithUserId(@Param("labelId") Long labelId, @Param("userId") Long userId);

    List<ArticleBaseInfoPojo> selectSortAboutArticleWithUserIdAndKey(@Param("sortId") Long sortId, @Param("userId") Long userId, @Param("key") String key);

    List<ArticleBaseInfoPojo> selectLabelAboutArticleWithUserIdAndKey(@Param("labelId") Long labelId, @Param("userId") Long userId, @Param("key") String key);

    List<ArticleWithUserBaseInfoPojo> selectArticleWithUserBaseInfoByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    List<ArticleBaseInfoPojo> selectAllArticleBaseInfoByUserId(Long userId);

    List<ArticleWithUserBaseInfoPojo> selectArticleByKey(String key);

    List<ArticleBaseInfoPojo> selectArticleBaseInfoByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    List<ArticleWithUserBaseInfoPojo> getRecommendArticle(int size);

    List<RecommendUserPojo> getRecommendUser(int size);

    Long selectUserIdByArticleId(Long articleId);

    int updateByExampleSelective(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}
