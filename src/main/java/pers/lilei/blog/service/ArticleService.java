package pers.lilei.blog.service;

import com.github.pagehelper.PageInfo;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo;

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

    PageInfo<ArticleWithUserBaseInfoPojo> selectArticleBaseInfoByKey(int pageNow, int pageSize, Long userId, String key);

    Integer addArticle(ArticleWithBLOBs articleWithBLOBs);

    Integer deleteArticleByArticleId(Long articleId);

    Integer updateArticle(ArticleWithBLOBs articleWithBLOBs);

    ArticleWithBLOBs getArticleByArticleId(Long articleId);
}
