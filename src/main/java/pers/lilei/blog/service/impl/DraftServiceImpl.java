package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.bean.DraftWithBLOBs;
import pers.lilei.blog.constant.DraftConstant;
import pers.lilei.blog.constant.ReturnConstant;
import pers.lilei.blog.dao.ArticleLabelMapper;
import pers.lilei.blog.dao.ArticleMapper;
import pers.lilei.blog.dao.ArticleSortMapper;
import pers.lilei.blog.dao.DraftMapper;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.DraftParam;
import pers.lilei.blog.service.ArticleLabelService;
import pers.lilei.blog.service.ArticleSortService;
import pers.lilei.blog.service.DraftService;

import java.util.Date;
import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>草稿</p>
 *
 * @author : 李雷
 * @date : 2021-04-19 11:42
 **/
@Service
public class DraftServiceImpl implements DraftService {
    DraftMapper draftMapper;
    ArticleMapper articleMapper;
    ArticleLabelMapper articleLabelMapper;
    ArticleSortMapper articleSortMapper;
    @Autowired
    public DraftServiceImpl(DraftMapper draftMapper, ArticleMapper articleMapper, ArticleLabelMapper articleLabelMapper, ArticleSortMapper articleSortMapper) {
        this.draftMapper = draftMapper;
        this.articleMapper = articleMapper;
        this.articleLabelMapper = articleLabelMapper;
        this.articleSortMapper = articleSortMapper;
    }

    @Override
    public List<DraftWithBLOBs> getUserDraft(DraftParam draftParam) {
        return draftMapper.selectUserDraft(draftParam);
    }

    @Override
    public DraftWithBLOBs selectByTitle(DraftParam draftParam) {
        return draftMapper.selectByTitle(draftParam);
    }

    /**
     * @description 添加博文
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @Override
    public int addDraft(DraftWithBLOBs draftWithBLOBs) {
        //超限
        DraftParam draftParam = new DraftParam();
        draftParam.setUserId(draftWithBLOBs.getUserId());
        if (ifOverNum(draftParam)) {
            return ReturnConstant.OVER_VALUE;
        }
        draftParam.setArticleTitle(draftWithBLOBs.getArticleTitle());
        //标题不允许重复
        if (selectByTitle(draftParam) != null) {
            return ReturnConstant.REPEAT_VALUE;
        }
        return draftMapper.insertSelective(draftWithBLOBs);
    }

    /**
     * @description 统计当前用户草稿数量
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @Override
    public boolean ifOverNum(DraftParam draftParam) {
        if (draftMapper.selectUserDraftCount(draftParam) >= DraftConstant.DRAFT_COUNT) {
            return true;
        }
        return false;
    }

    @Override
    public int updateDraft(DraftWithBLOBs draftWithBLOBs) {
        return draftMapper.updateByPrimaryKeySelective(draftWithBLOBs);
    }

    @Override
    public int deleteDraft(DraftParam draftParam) {
        //移除关联的所有标签
        if (articleLabelMapper.deleteAllDraftLabel(draftParam) < 0) {
            return -1;
        }
        //移除关联的所有分类
        if (articleSortMapper.deleteAllDraftSort(draftParam) < 0) {
            return -1;
        }
        return draftMapper.deleteDraft(draftParam);
    }

    @Override
    public int uploadDraft(DraftWithBLOBs draftWithBLOBs, ArticleWithBLOBs articleWithBLOBs) {
        articleWithBLOBs.setArticleTitle(draftWithBLOBs.getArticleTitle());
        articleWithBLOBs.setArticleContent(draftWithBLOBs.getArticleContent());
        articleWithBLOBs.setArticleDate(new Date());
        return articleMapper.insertSelective(articleWithBLOBs);
    }

    @Override
    public DraftWithBLOBs selectByDraftId(DraftParam draftParam) {
        return draftMapper.selectByPrimaryKey(draftParam.getDraftId());
    }
}
