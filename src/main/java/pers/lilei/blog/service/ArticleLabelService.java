package pers.lilei.blog.service;

import pers.lilei.blog.po.ArticleLabel;
import pers.lilei.blog.po.Label;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文标签服务类接口</p>
 *
 * @author : 李雷
 * @date : 2021-01-30 16:11
 **/
public interface ArticleLabelService {
    Integer addArticleLabel(ArticleLabel articleLabel);

    Integer deleteArticleLabel(Long articleId, Long labelId);

    List<Label> getAllArticleLabel(Long articleId);

    ArticleLabel selectByArticleIdAndLabelId(Long articleId, Long labelId);
}
