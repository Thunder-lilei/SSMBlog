package pers.lilei.blog.service;

import com.github.pagehelper.PageInfo;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.param.ArticleBaseInfoParam;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.ArticleWithUserBaseInfoParam;
import pers.lilei.blog.param.RecommendUserParam;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文Service层</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 12:17
 **/
public interface ArticleService {
    PageInfo<ArticleWithUserBaseInfoParam> selectAllArticleWithUserBaseInfoByUserId(int pageNow, int pageSize, Long userId);

    PageInfo<ArticleWithUserBaseInfoParam> selectArticleWithUserBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key);

    PageInfo<ArticleBaseInfoParam> selectAllArticleBaseInfoByUserId(int pageNow, int pageSize, Long userId);

    PageInfo<ArticleBaseInfoParam> selectArticleBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key);

    PageInfo<ArticleBaseInfoParam> getSortAboutArticleWithUserId(int pageNow, int pageSize, Long sortId, Long userId);

    PageInfo<ArticleBaseInfoParam> getLabelAboutArticleWithUserId(int pageNow, int pageSize, Long labelId, Long userId);

    PageInfo<ArticleBaseInfoParam> getSortAboutArticleWithUserIdAndKey(int pageNow, int pageSize, Long sortId, Long userId, String key);

    PageInfo<ArticleBaseInfoParam> getLabelAboutArticleWithUserIdAndKey(int pageNow, int pageSize, Long labelId, Long userId, String key);

    PageInfo<ArticleWithUserBaseInfoParam> searchArticle(int pageNow, int pageSize, String key);

    ArticleWithBLOBs selectByArticleTitle(String title);

    Integer addArticle(ArticleWithBLOBs articleWithBLOBs);

    Integer deleteArticleByArticleId(Long articleId);

    Integer updateArticle(ArticleWithBLOBs articleWithBLOBs);

    ArticleWithBLOBs getArticleByArticleId(Long articleId);

    List<ArticleWithUserBaseInfoParam> getRecommendArticle(int size);

    List<RecommendUserParam> getRecommendUser(int size);

    Long getUserIdByArticleId(Long articleId);

    int getArticleCommentNum(ArticleParam articleParam);

}
