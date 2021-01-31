package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.ArticleLabel;
import pers.lilei.blog.po.ArticleSort;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.po.Sort;
import pers.lilei.blog.service.ArticleSortService;
import pers.lilei.blog.service.SortService;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>博文分类控制层</p>
 *
 * @author : 李雷
 * @date : 2021-01-31 14:53
 **/
@Controller
@RequestMapping(value = "/articleSort")
public class ArticleSortController extends BaseController{
    ArticleSortService articleSortService;
    SortService sortService;
    @Autowired
    public ArticleSortController(ArticleSortService articleSortService, SortService sortService) {
        this.articleSortService = articleSortService;
        this.sortService = sortService;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取新分类信息 创建
     * 已拥有的分类则不添加
     * 创建关联
     * @CreateDate 20:10 2021/1/31
     * @UpdateDate 20:10 2021/1/31
     * @Param
     * @return
     **/
    @ResponseBody
    @RequestMapping(value = "/addArticleSort", method = RequestMethod.POST)
    private Map<String,Object> addArticleSort(@RequestParam String sortName, @RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        Sort sort = new Sort();
        sort.setSortName(sortName);
        Integer result = sortService.addSort(sort);
        //创建新分类 -1为已拥有的分类
        if (result.equals(1) || result.equals(-1)) {
            ArticleSort articleSort = new ArticleSort();
            articleSort.setArticleId(articleId);
            //获取刚刚添加的新分类或已存在的分类id
            articleSort.setSortId(sortService.selectBySortName(sort.getSortName()).getSortId());
            Integer addArticleSortResult = articleSortService.addArticleSort(articleSort);
            if (addArticleSortResult.equals(1) || addArticleSortResult.equals(-1)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "分类与博文关联失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "创建新分类失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 移除分类和博文的关联
     * @CreateDate 20:11 2021/1/31
     * @UpdateDate 20:11 2021/1/31
     * @Param [sortId, articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/removeArticleSort", method = RequestMethod.POST)
    private Map<String,Object> removeArticleSort(@RequestParam Long sortId, @RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        if (articleSortService.deleteArticleSort(articleId, sortId).equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "移除关联分类失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取博文分类
     * @CreateDate 19:51 2021/1/31
     * @UpdateDate 19:51 2021/1/31
     * @Param [articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getArticleSort", method = RequestMethod.POST)
    private Map<String,Object> getArticleSort(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("sortList", articleSortService.getAllArticleSort(articleId));
        return modelMap;
    }
}
