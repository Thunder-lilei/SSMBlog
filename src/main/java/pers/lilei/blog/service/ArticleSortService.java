package pers.lilei.blog.service;

import pers.lilei.blog.bean.ArticleSort;
import pers.lilei.blog.bean.Sort;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.DraftParam;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文分类服务类接口</p>
 *
 * @author : 李雷
 * @date : 2021-01-30 16:12
 **/
public interface ArticleSortService {
    Integer addArticleSortList(List<Sort> sortList, Long articleId);

    Integer addDraftSortList(List<Sort> sortList, Long draftId);

    Integer addArticleSort(ArticleSort articleSort);

    Integer addDraftSort(ArticleSort articleSort);

    Integer deleteArticleSort(Long articleId, Long sortId);

    Integer deleteDraftSort(Long draftId, Long sortId);

    int deleteAllArticleSort(ArticleParam articleParam);

    int deleteAllDraftSort(DraftParam draftParam);

    List<Sort> getAllArticleSort(Long articleId);

    List<Sort> getAllDraftSort(Long draftId);

    ArticleSort selectByArticleIdAndSortId(Long articleId, Long sortId);

    ArticleSort selectByDraftIdAndSortId(Long draftId, Long sortId);

    Integer updateArticleSortList(List<Sort> sortList, Long articleId);

    Integer updateDraftSortList(List<Sort> sortList, Long draftId);
}
