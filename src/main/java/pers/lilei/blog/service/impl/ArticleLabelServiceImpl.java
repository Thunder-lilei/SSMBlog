package pers.lilei.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.ArticleLabelMapper;
import pers.lilei.blog.dao.LabelMapper;
import pers.lilei.blog.po.ArticleLabel;
import pers.lilei.blog.po.Label;
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
}
