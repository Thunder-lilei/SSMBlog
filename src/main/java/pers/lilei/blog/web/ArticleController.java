package pers.lilei.blog.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.converters.StringArrayConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.po.Sort;
import pers.lilei.blog.po.User;
import pers.lilei.blog.service.*;

import java.util.*;

/**
 * <h3>SSMBlog</h3>
 * <p>博文Control层</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 12:15
 **/
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController{
    ArticleService articleService;
    ArticleLabelService articleLabelService;
    ArticleSortService articleSortService;
    @Autowired
    public ArticleController(ArticleService articleService, ArticleLabelService articleLabelService, ArticleSortService articleSortService) {
        this.articleService = articleService;
        this.articleLabelService = articleLabelService;
        this.articleSortService = articleSortService;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取所有登录用户的博文基本信息
     * @CreateDate 12:28 2021/1/2
     * @UpdateDate 12:28 2021/1/2
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/selectAllArticleBaseInfo", method = RequestMethod.POST)
    private Map<String,Object> selectAllArticleBaseInfo(@RequestParam int pageNow, @RequestParam int pageSize){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("articlePageInfo", articleService.selectAllArticleBaseInfoByUserId(pageNow, pageSize, user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 通过关键词获取所有登录用户的博文基本信息
     * @CreateDate 14:50 2021/1/5
     * @UpdateDate 14:50 2021/1/5
     * @Param [pageNow, pageSize, userId, key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/selectArticleBaseInfoByKey", method = RequestMethod.POST)
    private Map<String,Object> selectArticleBaseInfoByKey(@RequestParam Integer pageNow, @RequestParam Integer pageSize, @RequestParam String key) {
        Map<String, Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("articlePageInfo", articleService.selectArticleBaseInfoByUserIdAndKey(pageNow, pageSize, user.getUserId(), key));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description 添加博文
     * @CreateDate 18:37 2021/1/2
     * @UpdateDate 18:37 2021/1/2
     * @Param [articleWithBLOBs]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    private Map<String,Object> addArticle(@RequestBody ArticleWithBLOBs articleWithBLOBs, List<Sort> sortList, List<Label> labelList){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //设置博文所有者
            articleWithBLOBs.setUserId(user.getUserId());
            //获取本地时间设置创建时间
            articleWithBLOBs.setArticleDate(new Date());
            if (articleService.addArticle(articleWithBLOBs).equals(1)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "添加失败");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 删除博文
     * @CreateDate 18:45 2021/1/2
     * @UpdateDate 18:45 2021/1/2
     * @Param [articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    private Map<String,Object> deleteArticle(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        if (!articleService.deleteArticleByArticleId(articleId).equals(0)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "删除失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 修改博文
     * @CreateDate 18:51 2021/1/2
     * @UpdateDate 2021-2-1 17:51:24
     * @Param [articleWithBLOBs]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    private Map<String,Object> updateArticle(@RequestBody Map<String,Object> data){
        Map<String,Object> modelMap = new HashMap<>();
        List<Label> labelList = JSON.parseArray((String) data.get("labelList"),Label.class);
        List<Sort> sortList = JSON.parseArray((String) data.get("sortList"),Sort.class);
        ArticleWithBLOBs articleWithBLOBs = JSON.parseObject((String) data.get("article"), ArticleWithBLOBs.class);
        if (!articleService.updateArticle(articleWithBLOBs).equals(0)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "修改失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 通过博文序号获取博文
     * @CreateDate 18:55 2021/1/2
     * @UpdateDate 18:55 2021/1/2
     * @Param [articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/setShowArticle", method = RequestMethod.POST)
    private Map<String,Object> setShowArticle(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        ArticleWithBLOBs articleWithBLOBs = articleService.getArticleByArticleId(articleId);
        if (articleWithBLOBs != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            session.setAttribute("showArticle", articleWithBLOBs);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取博文 返回给前端
     * 增加博文访问量
     * @CreateDate 13:51 2021/1/5
     * @UpdateDate 13:51 2021/1/5
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getShowArticle", method = RequestMethod.POST)
    private Map<String,Object> getShowArticle(){
        Map<String,Object> modelMap = new HashMap<>();
        ArticleWithBLOBs articleWithBLOBs = (ArticleWithBLOBs) session.getAttribute("showArticle");
        if (articleWithBLOBs != null) {
            articleWithBLOBs.setArticleViews(articleWithBLOBs.getArticleViews() + 1);
            articleService.updateArticle(articleWithBLOBs);
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("article", articleWithBLOBs);
            session.setAttribute("showArticle", null);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取特定数量的推荐的博文
     * @CreateDate 13:25 2021/1/8
     * @UpdateDate 13:25 2021/1/8
     * @Param [size]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getRecommendArticle", method = RequestMethod.POST)
    private Map<String,Object> getRecommendArticle(@RequestParam int size){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("recommendArticleList", articleService.getRecommendArticle(size));
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取特定数量的推荐的博主
     * @CreateDate 22:34 2021/1/22
     * @UpdateDate 22:34 2021/1/22
     * @Param [size]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getRecommendUser", method = RequestMethod.POST)
    private Map<String,Object> getRecommendUser(@RequestParam int size){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("recommendUserList", articleService.getRecommendUser(size));
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 分页查询特定用户的所有博文
     * @CreateDate 22:54 2021/1/15
     * @UpdateDate 22:54 2021/1/15
     * @Param [pageNow, pageSize, userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getArticleByUserId", method = RequestMethod.POST)
    private Map<String,Object> getUserAllArticle(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam Long userId){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("articlePageInfo", articleService.selectAllArticleBaseInfoByUserId(pageNow, pageSize, userId));
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 分页查询特定用户的关键词匹配的所有博文
     * @CreateDate 22:54 2021/1/15
     * @UpdateDate 22:54 2021/1/15
     * @Param [pageNow, pageSize, userId, key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getArticleByUserIdAndKey", method = RequestMethod.POST)
    private Map<String,Object> getArticleByUserIdAndKey(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam Long userId, @RequestParam String key){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("articlePageInfo", articleService.selectArticleBaseInfoByUserIdAndKey(pageNow, pageSize, userId, key));
        return modelMap;
    }
}
