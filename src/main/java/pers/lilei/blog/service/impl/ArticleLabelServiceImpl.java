package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.ArticleLabelMapper;
import pers.lilei.blog.dao.LabelMapper;
import pers.lilei.blog.bean.ArticleLabel;
import pers.lilei.blog.bean.Label;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.DraftParam;
import pers.lilei.blog.service.ArticleLabelService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文标签服务实现类</p>
 *
 * @author : 李雷
 * @date : 2021-01-31 12:49
 **/
@Service
public class ArticleLabelServiceImpl implements ArticleLabelService {
    ArticleLabelMapper articleLabelMapper;
    LabelMapper labelMapper;
    @Autowired
    public ArticleLabelServiceImpl(ArticleLabelMapper articleLabelMapper, LabelMapper labelMapper) {
        this.articleLabelMapper = articleLabelMapper;
        this.labelMapper = labelMapper;
    }

    /**
     * @description 博文添加标签
     * @author lilei
     * @Time
     * @updateTime 2021/4/19
     */
    @Override
    public Integer addArticleLabelList(List<Label> labelList, Long articleId) {
        ArticleLabel articleLabel = new ArticleLabel();
        Label newLabel;
        for (Label label : labelList) {
            newLabel = labelMapper.selectByLabelName(label.getLabelName());
            //创建新分类
            if (newLabel == null) {
                newLabel = new Label();
                newLabel.setLabelName(label.getLabelName());
                labelMapper.insertSelective(newLabel);
            }            //为分类添加序号
            label.setLabelId(labelMapper.selectByLabelName(label.getLabelName()).getLabelId());

            articleLabel.setArticleId(articleId);
            articleLabel.setLabelId(label.getLabelId());
            addArticleLabel(articleLabel);
        }
        return 1;
    }

    @Override
    public Integer addDraftLabelList(List<Label> labelList, Long draftId) {
        ArticleLabel articleLabel = new ArticleLabel();
        Label newLabel;
        for (Label label : labelList) {
            newLabel = labelMapper.selectByLabelName(label.getLabelName());
            //创建新分类
            if (newLabel == null) {
                newLabel = new Label();
                newLabel.setLabelName(label.getLabelName());
                labelMapper.insertSelective(newLabel);
            }
            //为分类添加序号
            label.setLabelId(labelMapper.selectByLabelName(label.getLabelName()).getLabelId());

            articleLabel.setDraftId(draftId);
            articleLabel.setLabelId(label.getLabelId());
            addDraftLabel(articleLabel);
        }
        return 1;
    }

    @Override
    public Integer addArticleLabel(ArticleLabel articleLabel) {
        if (selectByArticleIdAndLabelId(articleLabel.getArticleId(), articleLabel.getLabelId()) != null) {
            return -1;
        }
        return articleLabelMapper.insertSelective(articleLabel);
    }

    @Override
    public Integer addDraftLabel(ArticleLabel articleLabel) {
        if (selectByDraftIdAndLabelId(articleLabel.getDraftId(), articleLabel.getLabelId()) != null) {
            return -1;
        }
        return articleLabelMapper.insertSelective(articleLabel);
    }

    @Override
    public Integer deleteArticleLabel(Long articleId, Long labelId) {
        return articleLabelMapper.deleteByArticleIdAndLabelId(articleId, labelId);
    }

    @Override
    public int deleteAllArticleLabel(ArticleParam articleParam) {
        return articleLabelMapper.deleteAllArticleLabel(articleParam);
    }

    @Override
    public int deleteAllDraftLabel(DraftParam draftParam) {
        return articleLabelMapper.deleteAllDraftLabel(draftParam);
    }

    @Override
    public Integer deleteDraftLabel(Long draftId, Long labelId) {
        return articleLabelMapper.deleteByDraftIdIdAndLabelId(draftId, labelId);
    }

    @Override
    public List<Label> getAllArticleLabel(Long articleId) {
        return labelMapper.selectLabelByArticleId(articleId);
    }

    @Override
    public List<Label> getAllDraftLabel(Long draftId) {
        return labelMapper.selectLabelByDraftId(draftId);
    }

    @Override
    public ArticleLabel selectByArticleIdAndLabelId(Long articleId, Long labelId) {
        return articleLabelMapper.selectByArticleIdAndLabelId(articleId, labelId);
    }

    @Override
    public ArticleLabel selectByDraftIdAndLabelId(Long draftId, Long labelId) {
        return articleLabelMapper.selectByDraftIdAndLabelId(draftId, labelId);
    }

    /*
     * @Author 李雷
     * @Description
     * 替换掉原本的标签列表
     * 需要优化算法
     * @CreateDate 18:12 2021/2/1
     * @UpdateDate 18:12 2021/2/1
     * @Param
     * @return
     **/
    @Override
    public Integer updateArticleLabelList(List<Label> labelList, Long articleId) {
        //获取原本关联的标签列表
        List<Label> oldLabelList = getAllArticleLabel(articleId);
        //添加新列表，不会重复添加
        addArticleLabelList(labelList, articleId);

        for (Label oldLabel : oldLabelList) {
            if (!labelList.contains(oldLabel)) {
                System.out.println("删除"+oldLabel.getLabelName());
                //移除旧列表中多余的标签
                deleteArticleLabel(articleId, oldLabel.getLabelId());
            }
        }
        return 1;
    }
    /**
     * @description 更新标签列表
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @Override
    public Integer updateDraftLabelList(List<Label> labelList, Long draftId) {
        //获取原本关联的标签列表
        List<Label> oldLabelList = getAllDraftLabel(draftId);
        //添加新列表，不会重复添加
        addDraftLabelList(labelList, draftId);

        for (Label oldLabel : oldLabelList) {
            if (!labelList.contains(oldLabel)) {
                System.out.println("删除"+oldLabel.getLabelName());
                //移除旧列表中多余的标签
                deleteDraftLabel(draftId, oldLabel.getLabelId());
            }
        }
        return 1;
    }
}
