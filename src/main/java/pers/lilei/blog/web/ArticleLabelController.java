package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.ArticleLabel;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.service.ArticleLabelService;
import pers.lilei.blog.service.LabelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>博文标签控制层</p>
 *
 * @author : 李雷
 * @date : 2021-01-31 14:51
 **/
@Controller
@RequestMapping(value = "/articleLabel")
public class ArticleLabelController extends BaseController{
    ArticleLabelService articleLabelService;
    LabelService labelService;
    @Autowired
    public ArticleLabelController(ArticleLabelService articleLabelService, LabelService labelService) {
        this.articleLabelService = articleLabelService;
        this.labelService = labelService;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取新标签信息 创建
     * 已拥有的标签则不添加
     * 创建关联
     * @CreateDate 15:02 2021/1/31
     * @UpdateDate 15:02 2021/1/31
     * @Param [label, articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addArticleLabel", method = RequestMethod.POST)
    private Map<String,Object> addArticleLabel(@RequestParam String labelName, @RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        Label label = new Label();
        label.setLabelName(labelName);
        Integer result = labelService.addLabel(label);
        //创建新标签 -1为已拥有的标签
        if (result.equals(1) || result.equals(-1)) {
            ArticleLabel articleLabel = new ArticleLabel();
            articleLabel.setArticleId(articleId);
            //获取刚刚添加的新标签或已存在的标签id
            articleLabel.setLabelId(labelService.selectByLabelName(label.getLabelName()).getLabelId());
            Integer addArticleLabelResult = articleLabelService.addArticleLabel(articleLabel);
            if (addArticleLabelResult.equals(1) || addArticleLabelResult.equals(-1)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "标签与博文关联失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "创建新标签失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 移除标签和博文的关联
     * @CreateDate 19:14 2021/1/31
     * @UpdateDate 19:14 2021/1/31
     * @Param [labelId, articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/removeArticleLabel", method = RequestMethod.POST)
    private Map<String,Object> removeArticleLabel(@RequestParam Long labelId, @RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        if (articleLabelService.deleteArticleLabel(articleId, labelId).equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "移除关联标签失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description获取博文标签
     * @CreateDate 19:50 2021/1/31
     * @UpdateDate 19:50 2021/1/31
     * @Param [articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getArticleLabel", method = RequestMethod.POST)
    private Map<String,Object> getArticleLabel(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("labelList", articleLabelService.getAllArticleLabel(articleId));
        return modelMap;
    }
}
