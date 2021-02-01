package pers.lilei.blog.service;

import com.github.pagehelper.PageInfo;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.pojo.ArticleBaseInfoPojo;
import pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo;
import pers.lilei.blog.pojo.RecommendUserPojo;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文Service层</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 12:17
 **/
public interface ArticleService {
    PageInfo<ArticleWithUserBaseInfoPojo> selectAllArticleWithUserBaseInfoByUserId(int pageNow, int pageSize, Long userId);

    PageInfo<ArticleWithUserBaseInfoPojo> selectArticleWithUserBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key);

    PageInfo<ArticleBaseInfoPojo> selectAllArticleBaseInfoByUserId(int pageNow, int pageSize, Long userId);

    PageInfo<ArticleBaseInfoPojo> selectArticleBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key);

    ArticleWithBLOBs selectByArticleTitle(String title);

    Integer addArticle(ArticleWithBLOBs articleWithBLOBs);

    Integer deleteArticleByArticleId(Long articleId);

    Integer updateArticle(ArticleWithBLOBs articleWithBLOBs);

    ArticleWithBLOBs getArticleByArticleId(Long articleId);

    List<ArticleWithUserBaseInfoPojo> getRecommendArticle(int size);

    List<RecommendUserPojo> getRecommendUser(int size);
}
