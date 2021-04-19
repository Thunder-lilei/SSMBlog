package pers.lilei.blog.web;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lilei.blog.bean.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.constant.ReturnConstant;
import pers.lilei.blog.param.DraftParam;
import pers.lilei.blog.service.ArticleLabelService;
import pers.lilei.blog.service.ArticleService;
import pers.lilei.blog.service.ArticleSortService;
import pers.lilei.blog.service.DraftService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>草稿api</p>
 *
 * @author : 李雷
 * @date : 2021-04-19 12:41
 **/
@Controller
@RequestMapping(value = "/draft")
public class DraftController extends BaseController{
    DraftService draftService;
    ArticleLabelService articleLabelService;
    ArticleSortService articleSortService;
    ArticleService articleService;
    @Autowired
    public DraftController(DraftService draftService, ArticleLabelService articleLabelService, ArticleSortService articleSortService, ArticleService articleService) {
        this.draftService = draftService;
        this.articleLabelService = articleLabelService;
        this.articleSortService = articleSortService;
        this.articleService = articleService;
    }

    /**
     * @description 添加草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/addDraft", method = RequestMethod.POST)
    private Map<String,Object> addDraft(@RequestBody Map<String, Object> data){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Label> checkedLabelList = JSON.parseArray((String) data.get("labelList"),Label.class);
            List<Sort> checkedSortList = JSON.parseArray((String) data.get("sortList"),Sort.class);
            DraftWithBLOBs draftWithBLOBs = JSON.parseObject((String) data.get("draft"), DraftWithBLOBs.class);
            //设置博文所有者
            draftWithBLOBs.setUserId(user.getUserId());
            Integer result = draftService.addDraft(draftWithBLOBs);
            if (result > 0) {
                DraftParam draftParam = new DraftParam();
                draftParam.setArticleTitle(draftWithBLOBs.getArticleTitle());
                draftWithBLOBs = draftService.selectByTitle(draftParam);
                //添加分类
                articleSortService.addDraftSortList(checkedSortList, draftWithBLOBs.getDraftId());
                //添加标签
                articleLabelService.addDraftLabelList(checkedLabelList, draftWithBLOBs.getDraftId());
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else if (result.equals(ReturnConstant.REPEAT_VALUE)) {
                modelMap.put(MessageConstant.MESSAGE, "草稿博文标题重复！");
            } else if (result.equals(ReturnConstant.OVER_VALUE)){
                modelMap.put(MessageConstant.MESSAGE, "超出草稿保存上限10");
            } else {
                modelMap.put(MessageConstant.MESSAGE, "保存失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }

    /**
     * @description 获取当前用户草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/getDraftList", method = RequestMethod.POST)
    private Map<String,Object> getDraftList(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            DraftParam draftParam = new DraftParam();
            draftParam.setUserId(user.getUserId());
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("draftList", draftService.getUserDraft(draftParam));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /**
     * @description 删除草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDraft", method = RequestMethod.POST)
    private Map<String,Object> deleteDraft(@RequestBody DraftParam draftParam){
        Map<String,Object> modelMap = new HashMap<>();
        if (draftService.deleteDraft(draftParam) > 0) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "删除失败！");
        }
        return modelMap;
    }
    /**
     * @description 修改草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/updateDraft", method = RequestMethod.POST)
    private Map<String,Object> updateDraft(@RequestBody Map<String,Object> data){
        Map<String,Object> modelMap = new HashMap<>();
        List<Label> checkedLabelList = JSON.parseArray((String) data.get("labelList"),Label.class);
        List<Sort> checkedSortList = JSON.parseArray((String) data.get("sortList"),Sort.class);
        DraftWithBLOBs draftWithBLOBs = JSON.parseObject((String) data.get("draft"), DraftWithBLOBs.class);
        //更新标签、分类列表
        articleLabelService.updateDraftLabelList(checkedLabelList, draftWithBLOBs.getDraftId());
        articleSortService.updateDraftSortList(checkedSortList, draftWithBLOBs.getDraftId());
        if (draftService.updateDraft(draftWithBLOBs) > 0) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "修改失败！");
        }
        return modelMap;
    }
    /**
     * @description 获取草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/getDraft", method = RequestMethod.POST)
    private Map<String,Object> getDraft(@RequestBody DraftParam draftParam){
        Map<String,Object> modelMap = new HashMap<>();
        DraftWithBLOBs draftWithBLOBs = draftService.selectByDraftId(draftParam);
        if (draftWithBLOBs != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("draft", draftWithBLOBs);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "草稿查询失败");
        }
        return modelMap;
    }
    /**
     * @description 设置需要展示的草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/setShowDraft", method = RequestMethod.POST)
    private Map<String,Object> setShowDraft(@RequestBody DraftParam draftParam){
        Map<String,Object> modelMap = new HashMap<>();
        DraftWithBLOBs draftWithBLOBs = draftService.selectByDraftId(draftParam);
        if (draftWithBLOBs != null) {
            session.setAttribute("showDraft", draftWithBLOBs);
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未能查询到需要编辑的草稿！");
        }
        return modelMap;
    }
    /**
     * @description 获取需要展示的草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/getShowDraft", method = RequestMethod.POST)
    private Map<String,Object> getShowDraft(){
        Map<String,Object> modelMap = new HashMap<>();
        DraftWithBLOBs draftWithBLOBs = (DraftWithBLOBs) session.getAttribute("showDraft");
        if (draftWithBLOBs != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("showDraft", draftWithBLOBs);
            session.removeAttribute("showDraft");
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取编辑草稿失败！");
        }
        return modelMap;
    }
    /**
     * @description 发布草稿
     * @author lilei
     * @Time 2021/4/19
     * @updateTime 2021/4/19
     */
    @ResponseBody
    @RequestMapping(value = "/uploadDraft", method = RequestMethod.POST)
    private Map<String,Object> uploadDraft(@RequestBody DraftParam draftParam){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            DraftWithBLOBs draftWithBLOBs = draftService.selectByDraftId(draftParam);
            ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
            //设置博文作者
            articleWithBLOBs.setUserId(user.getUserId());
            //发布博文
            if (draftService.uploadDraft(draftWithBLOBs, articleWithBLOBs) > 0) {
                articleWithBLOBs = articleService.selectByArticleTitle(articleWithBLOBs.getArticleTitle());
                //获取草稿的分类、标签
                List<Label> checkedLabelList = articleLabelService.getAllDraftLabel(draftParam.getDraftId());
                List<Sort> checkedSortList = articleSortService.getAllDraftSort(draftParam.getDraftId());
                //博文关联分类、标签
                articleSortService.addArticleSortList(checkedSortList, articleWithBLOBs.getArticleId());
                articleLabelService.addArticleLabelList(checkedLabelList, articleWithBLOBs.getArticleId());
                //移除草稿
                draftService.deleteDraft(draftParam);
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "博文发布失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
}
