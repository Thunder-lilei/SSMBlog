package pers.lilei.blog.web;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.bean.Label;
import pers.lilei.blog.bean.Sort;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.param.ArticleParam;
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
    private Map<String,Object> addArticle(@RequestBody Map<String, Object> data){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Label> checkedLabelList = JSON.parseArray((String) data.get("labelList"),Label.class);
            List<Sort> checkedSortList = JSON.parseArray((String) data.get("sortList"),Sort.class);
            ArticleWithBLOBs articleWithBLOBs = JSON.parseObject((String) data.get("article"), ArticleWithBLOBs.class);
            //设置博文所有者
            articleWithBLOBs.setUserId(user.getUserId());
            //获取本地时间设置创建时间
            articleWithBLOBs.setArticleDate(new Date());
            Integer result = articleService.addArticle(articleWithBLOBs);
            if (result.equals(1)) {
                articleWithBLOBs = articleService.selectByArticleTitle(articleWithBLOBs.getArticleTitle());
                articleSortService.addArticleSortList(checkedSortList, articleWithBLOBs.getArticleId());
                articleLabelService.addArticleLabelList(checkedLabelList, articleWithBLOBs.getArticleId());
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else if (result.equals(-1)) {
                modelMap.put(MessageConstant.MESSAGE, "博文标题重复！");
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
        List<Label> checkedLabelList = JSON.parseArray((String) data.get("labelList"),Label.class);
        List<Sort> checkedSortList = JSON.parseArray((String) data.get("sortList"),Sort.class);
        ArticleWithBLOBs articleWithBLOBs = JSON.parseObject((String) data.get("article"), ArticleWithBLOBs.class);
        //更新标签、分类列表
        articleLabelService.updateArticleLabelList(checkedLabelList, articleWithBLOBs.getArticleId());
        articleSortService.updateArticleSortList(checkedSortList, articleWithBLOBs.getArticleId());
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
    /*
     * @Author 李雷
     * @Description
     * 分页获取登录用户所选分类的所有博文基本信息
     * @CreateDate 20:53 2021/2/6
     * @UpdateDate 20:53 2021/2/6
     * @Param [pageNow, pageSize, sortId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getSortAboutArticle", method = RequestMethod.POST)
    private Map<String,Object> getSortAboutArticle(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam Long sortId){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("articlePageInfo", articleService.getSortAboutArticleWithUserId(pageNow, pageSize, sortId, user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 分页获取登录用户所选标签的所有博文基本信息
     * @CreateDate 20:54 2021/2/6
     * @UpdateDate 20:54 2021/2/6
     * @Param [pageNow, pageSize, labelId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getLabelAboutArticle", method = RequestMethod.POST)
    private Map<String,Object> getLabelAboutArticle(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam Long labelId){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("articlePageInfo", articleService.getLabelAboutArticleWithUserId(pageNow, pageSize, labelId, user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 分页获取登录用户所选分类以及关键词的所有博文基本信息
     * @CreateDate 20:54 2021/2/6
     * @UpdateDate 20:54 2021/2/6
     * @Param [pageNow, pageSize, sortId, key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getSortAboutArticleAndKey", method = RequestMethod.POST)
    private Map<String,Object> getSortAboutArticleAndKey(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam Long sortId, @RequestParam String key){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("articlePageInfo", articleService.getSortAboutArticleWithUserIdAndKey(pageNow, pageSize, sortId, user.getUserId(), key));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 分页获取登录用户所选标签以及关键词的所有博文基本信息
     * @CreateDate 20:55 2021/2/6
     * @UpdateDate 20:55 2021/2/6
     * @Param [pageNow, pageSize, labelId, key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getLabelAboutArticleAndKey", method = RequestMethod.POST)
    private Map<String,Object> getLabelAboutArticleAndKey(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam Long labelId, @RequestParam String key){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("articlePageInfo", articleService.getLabelAboutArticleWithUserIdAndKey(pageNow, pageSize, labelId, user.getUserId(), key));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 存储临时分类id
     * @CreateDate 21:52 2021/2/6
     * @UpdateDate 21:52 2021/2/6
     * @Param [sortId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/setSortId", method = RequestMethod.POST)
    private Map<String,Object> setSortId(@RequestParam Long sortId){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        session.setAttribute("sortId", sortId);
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 存储临时标签id
     * @CreateDate 21:51 2021/2/6
     * @UpdateDate 21:51 2021/2/6
     * @Param
     * @return
     **/
    @ResponseBody
    @RequestMapping(value = "/setLabelId", method = RequestMethod.POST)
    private Map<String,Object> setLabelId(@RequestParam Long labelId){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        session.setAttribute("labelId", labelId);
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取临时分类id
     * @CreateDate 21:51 2021/2/6
     * @UpdateDate 21:51 2021/2/6
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getSortId", method = RequestMethod.POST)
    private Map<String,Object> getSortId(){
        Map<String,Object> modelMap = new HashMap<>();
        Long sortId = (Long) session.getAttribute("sortId");
        if (sortId != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("sortId", sortId);
            session.setAttribute("sortId", null);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取临时标签id
     * @CreateDate 21:51 2021/2/6
     * @UpdateDate 21:51 2021/2/6
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getLabelId", method = RequestMethod.POST)
    private Map<String,Object> getLabelId(){
        Map<String,Object> modelMap = new HashMap<>();
        Long labelId = (Long) session.getAttribute("labelId");
        if (labelId != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("labelId", labelId);
            session.setAttribute("labelId", null);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 根据关键词搜索博文
     * @CreateDate 19:06 2021/2/8
     * @UpdateDate 19:06 2021/2/8
     * @Param [pageNow, pageSize, key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/searchArticle", method = RequestMethod.POST)
    private Map<String,Object> searchArticle(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam String key){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("articlePageInfo", articleService.searchArticle(pageNow, pageSize, key));
        return modelMap;
    }
    @ResponseBody
    @RequestMapping(value = "/ifMyArticle", method = RequestMethod.POST)
    private Map<String,Object> ifMyArticle(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("result", articleService.getUserIdByArticleId(articleId).equals(user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /**
     * @Author 李雷
     * @Description 获取博文评论总数
     * @CreateDate 16:38 2021/4/17
     * @UpdateDate 16:38 2021/4/17
     * @Param [articleParam]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getArticleCommentNum", method = RequestMethod.POST)
    private Map<String,Object> getArticleCommentNum(@RequestBody ArticleParam articleParam){
        Map<String,Object> modelMap = new HashMap<>();
        if (articleParam != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("articleCommentNum", articleService.getArticleCommentNum(articleParam));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "错误的参数");
        }
        return modelMap;
    }
}
