package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.constant.ReturnConstant;
import pers.lilei.blog.dao.ArticleSortMapper;
import pers.lilei.blog.dao.SortMapper;
import pers.lilei.blog.bean.ArticleSort;
import pers.lilei.blog.bean.Sort;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.DraftParam;
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
    public Integer addDraftSortList(List<Sort> sortList, Long draftId) {
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

            articleSort.setDraftId(draftId);
            articleSort.setSortId(sort.getSortId());
            addDraftSort(articleSort);
        }
        return 1;
    }

    @Override
    public Integer addArticleSort(ArticleSort articleSort) {
        if (selectByArticleIdAndSortId(articleSort.getArticleId(), articleSort.getSortId()) != null) {
            return ReturnConstant.REPEAT_VALUE;
        }
        return articleSortMapper.insertSelective(articleSort);
    }

    @Override
    public Integer addDraftSort(ArticleSort articleSort) {
        if (selectByDraftIdAndSortId(articleSort.getDraftId(), articleSort.getSortId()) != null) {
            return ReturnConstant.REPEAT_VALUE;
        }
        return articleSortMapper.insertSelective(articleSort);
    }

    @Override
    public Integer deleteArticleSort(Long articleId, Long sortId) {
        return articleSortMapper.deleteByArticleIdAndSortId(articleId, sortId);
    }

    @Override
    public Integer deleteDraftSort(Long draftId, Long sortId) {
        return articleSortMapper.deleteByDraftIdIdAndSortId(draftId, sortId);
    }

    @Override
    public int deleteAllDraftSort(DraftParam draftParam) {
        return articleSortMapper.deleteAllDraftSort(draftParam);
    }

    @Override
    public int deleteAllArticleSort(ArticleParam articleParam) {
        return articleSortMapper.deleteAllArticleSort(articleParam);
    }


    @Override
    public List<Sort> getAllArticleSort(Long articleId) {
        return sortMapper.selectSortByArticleId(articleId);
    }

    @Override
    public List<Sort> getAllDraftSort(Long draftId) {
        return sortMapper.selectSortByDraftId(draftId);
    }

    @Override
    public ArticleSort selectByArticleIdAndSortId(Long articleId, Long sortId) {
        return articleSortMapper.selectByArticleIdAndSortId(articleId, sortId);
    }

    @Override
    public ArticleSort selectByDraftIdAndSortId(Long draftId, Long sortId) {
        return articleSortMapper.selectByDraftIdAndSortId(draftId, sortId);
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

    /**
     * @description 更新草稿分类列表
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @Override
    public Integer updateDraftSortList(List<Sort> sortList, Long draftId) {
        //获取原本关联的分类列表
        List<Sort> oldSortList = getAllDraftSort(draftId);
        //添加新列表，不会重复添加
        addDraftSortList(sortList, draftId);

        for (Sort oldSort : oldSortList) {
            System.out.println(oldSort.getSortName());
            System.out.println(sortList.contains(oldSort));
            if (!sortList.contains(oldSort)) {
                System.out.println("删除"+oldSort.getSortName());
                //移除旧列表中多余的分类
                deleteDraftSort(draftId, oldSort.getSortId());
            }
        }
        return 1;
    }
}
