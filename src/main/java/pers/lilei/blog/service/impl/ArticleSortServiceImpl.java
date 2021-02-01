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
    public Integer addArticleSortList(List<Sort> sortList, Long articleId) {
        ArticleSort articleSort = new ArticleSort();
        Sort newSort;
        for (Sort sort : sortList) {
            newSort = sortMapper.selectBySortName(sort.getSortName());
            //创建新分类
            if (newSort == null) {
                newSort = new Sort();
                newSort.setSortName(sort.getSortName());
                sortMapper.insertSelective(newSort);
            }
            //为分类添加序号
            sort.setSortId(sortMapper.selectBySortName(sort.getSortName()).getSortId());

            articleSort.setArticleId(articleId);
            articleSort.setSortId(sort.getSortId());
            addArticleSort(articleSort);
        }
        return 1;
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

    /*
     * @Author 李雷
     * @Description
     * 替换掉原本的分类列表
     * 需要优化算法
     * @CreateDate 19:30 2021/2/1
     * @UpdateDate 19:30 2021/2/1
     * @Param [sortList, articleId]
     * @return java.lang.Integer
     **/
    @Override
    public Integer updateArticleSortList(List<Sort> sortList, Long articleId) {
        //获取原本关联的分类列表
        List<Sort> oldSortList = getAllArticleSort(articleId);
        //添加新列表，不会重复添加
        addArticleSortList(sortList, articleId);

        for (Sort oldSort : oldSortList) {
            System.out.println(oldSort.getSortName());
            System.out.println(sortList.contains(oldSort));
            if (!sortList.contains(oldSort)) {
                System.out.println("删除"+oldSort.getSortName());
                //移除旧列表中多余的分类
                deleteArticleSort(articleId, oldSort.getSortId());
            }
        }
        return 1;
    }
}
