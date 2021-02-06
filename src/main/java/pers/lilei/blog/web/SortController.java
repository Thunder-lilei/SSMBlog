package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.Sort;
import pers.lilei.blog.po.User;
import pers.lilei.blog.service.SortService;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>分类Control层</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:13
 **/
@Controller
@RequestMapping(value = "/sort")
public class SortController extends BaseController{
    SortService sortService;
    @Autowired
    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    /*
     * @Author 李雷
     * @Description
     * 添加标签 避免重复添加
     * @CreateDate 11:57 2021/1/30
     * @UpdateDate 11:57 2021/1/30
     * @Param [sort]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addSort", method = RequestMethod.POST)
    private Map<String,Object> addSort(@RequestBody Sort sort){
        Map<String,Object> modelMap = new HashMap<>();
        Integer result = sortService.addSort(sort);
        if (result.equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else if (result.equals(-1)) {
            modelMap.put(MessageConstant.MESSAGE, "重复添加！");
        } else {
            modelMap.put(MessageConstant.MESSAGE, "添加标签失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 移除标签
     * @CreateDate 12:04 2021/1/30
     * @UpdateDate 12:04 2021/1/30
     * @Param [sortId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/deleteSort", method = RequestMethod.POST)
    private Map<String,Object> deleteSort(@RequestParam Long sortId){
        Map<String,Object> modelMap = new HashMap<>();
        if (sortService.deleteSort(sortId).equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "移除标签失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 更新标签信息 避免重复标签
     * @CreateDate 12:13 2021/1/30
     * @UpdateDate 12:13 2021/1/30
     * @Param [sort]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updateSort", method = RequestMethod.POST)
    private Map<String,Object> updateSort(@RequestBody Sort sort){
        Map<String,Object> modelMap = new HashMap<>();
        Integer result = sortService.updateSort(sort);
        if (result.equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else if(result.equals(-1)) {
            modelMap.put(MessageConstant.MESSAGE, "重复的标签名");
        } else {
            modelMap.put(MessageConstant.MESSAGE, "更新标签信息失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取所有的标签
     * @CreateDate 14:05 2021/1/30
     * @UpdateDate 14:05 2021/1/30
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getAllSort", method = RequestMethod.POST)
    private Map<String,Object> getAllSort(){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("sortList", sortService.getAllSort());
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取登录用户使用的所有分类
     * @CreateDate 12:15 2021/2/6
     * @UpdateDate 12:15 2021/2/6
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getMySort", method = RequestMethod.POST)
    private Map<String,Object> getMySort(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("sortList", sortService.getMySort(user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
}
