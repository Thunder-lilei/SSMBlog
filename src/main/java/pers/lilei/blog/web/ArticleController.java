package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.Article;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.po.User;
import pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo;
import pers.lilei.blog.service.ArticleService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
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
            modelMap.put("articleWithUserPageInfo", articleService.selectAllArticleWithUserBaseInfoByUserId(pageNow, pageSize, user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description 添加博文
     * 暂时不考虑重复标题问题
     * @CreateDate 18:37 2021/1/2
     * @UpdateDate 18:37 2021/1/2
     * @Param [articleWithBLOBs]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    private Map<String,Object> addArticle(@RequestBody ArticleWithBLOBs articleWithBLOBs){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //设置博文所有者
            articleWithBLOBs.setUserId(user.getUserId());
            //获取本地时间设置创建时间
            articleWithBLOBs.setArticleDate(new Date());
            if (!articleService.addArticle(articleWithBLOBs).equals(0)) {
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
     * @UpdateDate 18:51 2021/1/2
     * @Param [articleWithBLOBs]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    private Map<String,Object> updateArticle(@RequestBody ArticleWithBLOBs articleWithBLOBs){
        Map<String,Object> modelMap = new HashMap<>();
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
    @RequestMapping(value = "/getArticle", method = RequestMethod.POST)
    private Map<String,Object> getArticle(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        ArticleWithBLOBs articleWithBLOBs = articleService.getArticleByArticleId(articleId);
        if (articleWithBLOBs != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("article", articleWithBLOBs);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取失败！");
        }
        return modelMap;
    }
}
