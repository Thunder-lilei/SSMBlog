package pers.lilei.blog.service;

import pers.lilei.blog.po.ArticleSort;
import pers.lilei.blog.po.Sort;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文分类服务类接口</p>
 *
 * @author : 李雷
 * @date : 2021-01-30 16:12
 **/
public interface ArticleSortService {
    Integer addArticleSort(ArticleSort articleSort);

    Integer deleteArticleSort(Long articleId, Long sortId);

    List<Sort> getAllArticleSort(Long articleId);

    ArticleSort selectByArticleIdAndSortId(Long articleID, Long sortId);
}
