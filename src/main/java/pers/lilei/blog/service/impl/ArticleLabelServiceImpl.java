package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.ArticleLabelMapper;
import pers.lilei.blog.dao.LabelMapper;
import pers.lilei.blog.po.ArticleLabel;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.service.ArticleLabelService;

import java.awt.font.FontRenderContext;
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

    @Override
    public Integer addArticleLabel(ArticleLabel articleLabel) {
        if (selectByArticleIdAndLabelId(articleLabel.getArticleId(), articleLabel.getLabelId()) != null) {
            return -1;
        }
        return articleLabelMapper.insertSelective(articleLabel);
    }

    @Override
    public Integer deleteArticleLabel(Long articleId, Long labelId) {
        return articleLabelMapper.deleteByArticleIdAndLabelId(articleId, labelId);
    }

    @Override
    public List<Label> getAllArticleLabel(Long articleId) {
        return labelMapper.selectLabelByArticleId(articleId);
    }

    @Override
    public ArticleLabel selectByArticleIdAndLabelId(Long articleId, Long labelId) {
        return articleLabelMapper.selectByArticleIdAndLabelId(articleId, labelId);
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
        for (Label label : labelList) {
            ArticleLabel articleLabel = new ArticleLabel();
            Label newLabel = labelMapper.selectByLabelName(label.getLabelName());
            //创建新标签
            if (newLabel == null) {
                newLabel = new Label();
                newLabel.setLabelName(label.getLabelName());
                labelMapper.insertSelective(newLabel);
                //为标签添加序号
                label.setLabelId(labelMapper.selectByLabelName(label.getLabelName()).getLabelId());
            } else {
                //为标签添加序号
                label.setLabelId(newLabel.getLabelId());
            }
            articleLabel.setArticleId(articleId);
            articleLabel.setLabelId(label.getLabelId());
            addArticleLabel(articleLabel);
        }
        for (Label oldLabel : oldLabelList) {
            if (!labelList.contains(oldLabel)) {
                System.out.println("删除"+oldLabel.getLabelName());
                //移除旧列表中多余的标签
                deleteArticleLabel(articleId, oldLabel.getLabelId());
            }
        }
        return 1;
    }
}
