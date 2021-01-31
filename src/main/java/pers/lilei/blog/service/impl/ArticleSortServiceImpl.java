package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.ArticleSortMapper;
import pers.lilei.blog.dao.SortMapper;
import pers.lilei.blog.po.ArticleSort;
import pers.lilei.blog.po.Sort;
import pers.lilei.blog.service.ArticleSortService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文分类服务实现类</p>
 *
 * @author : 李雷
 * @date : 2021-01-31 12:49
 **/
@Service
public class ArticleSortServiceImpl implements ArticleSortService {
    ArticleSortMapper articleSortMapper;
    SortMapper sortMapper;
    @Autowired
    public ArticleSortServiceImpl(ArticleSortMapper articleSortMapper, SortMapper sortMapper) {
        this.articleSortMapper = articleSortMapper;
        this.sortMapper = sortMapper;
    }

    @Override
    public Integer addArticleSort(ArticleSort articleSort) {
        if (selectByArticleIdAndSortId(articleSort.getArticleId(), articleSort.getSortId()) != null) {
            return -1;
        }
        return articleSortMapper.insertSelective(articleSort);
    }

    @Override
    public Integer deleteArticleSort(Long articleId, Long sortId) {
        return articleSortMapper.deleteByArticleIdAndSortId(articleId, sortId);
    }

    @Override
    public List<Sort> getAllArticleSort(Long articleId) {
        return sortMapper.selectSortByArticleId(articleId);
    }

    @Override
    public ArticleSort selectByArticleIdAndSortId(Long articleID, Long sortId) {
        return articleSortMapper.selectByArticleIdAndSortId(articleID, sortId);
    }
}
