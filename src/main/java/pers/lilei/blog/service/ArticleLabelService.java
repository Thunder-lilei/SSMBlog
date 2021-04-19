package pers.lilei.blog.service;

import pers.lilei.blog.bean.ArticleLabel;
import pers.lilei.blog.bean.Label;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.DraftParam;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文标签服务类接口</p>
 *
 * @author : 李雷
 * @date : 2021-01-30 16:11
 **/
public interface ArticleLabelService {
    Integer addArticleLabelList(List<Label> labelList, Long articleId);

    Integer addDraftLabelList(List<Label> labelList, Long draftId);

    Integer addArticleLabel(ArticleLabel articleLabel);

    Integer addDraftLabel(ArticleLabel articleLabel);

    Integer deleteArticleLabel(Long articleId, Long labelId);

    int deleteAllArticleLabel(ArticleParam articleParam);

    int deleteAllDraftLabel(DraftParam draftParam);

    Integer deleteDraftLabel(Long articleId, Long draftId);

    List<Label> getAllArticleLabel(Long articleId);

    List<Label> getAllDraftLabel(Long draftId);

    ArticleLabel selectByArticleIdAndLabelId(Long articleId, Long labelId);

    ArticleLabel selectByDraftIdAndLabelId(Long draftId, Long labelId);

    Integer updateArticleLabelList(List<Label> labelList, Long articleId);

    Integer updateDraftLabelList(List<Label> labelList, Long draftId);
}
