package pers.lilei.blog.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.Article;
import pers.lilei.blog.po.ArticleExample;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.pojo.ArticleBaseInfoPojo;
import pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo;

public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    List<ArticleWithBLOBs> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    ArticleWithBLOBs selectByPrimaryKey(Long articleId);

    List<ArticleWithUserBaseInfoPojo> selectAllArticleWithUserBaseInfoByUserId(Long userId);

    List<ArticleWithUserBaseInfoPojo> selectArticleWithUserBaseInfoByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    List<ArticleBaseInfoPojo> selectAllArticleBaseInfoByUserId(Long userId);

    List<ArticleBaseInfoPojo> selectArticleBaseInfoByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    List<ArticleWithUserBaseInfoPojo> getRecommendArticle(int size);

    int updateByExampleSelective(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}
