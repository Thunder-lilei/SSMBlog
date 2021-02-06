package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.po.User;
import pers.lilei.blog.service.LabelService;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>标签Control层</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:12
 **/
@Controller
@RequestMapping(value = "/label")
public class LabelController extends BaseController{
    LabelService labelService;
    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }
    /*
     * @Author 李雷
     * @Description
     * 添加标签
     * @CreateDate 14:42 2021/1/30
     * @UpdateDate 14:42 2021/1/30
     * @Param [label]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addLabel", method = RequestMethod.POST)
    private Map<String,Object> addLabel(@RequestBody Label label){
        Map<String,Object> modelMap = new HashMap<>();
        Integer result = labelService.addLabel(label);
        if (result.equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else if (result.equals(-1)) {
            modelMap.put(MessageConstant.MESSAGE, "标签名重复！");
        } else {
            modelMap.put(MessageConstant.MESSAGE, "标签添加失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 移除标签
     * @CreateDate 14:43 2021/1/30
     * @UpdateDate 14:43 2021/1/30
     * @Param [labelId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/deleteLabel", method = RequestMethod.POST)
    private Map<String,Object> deleteLabel(@RequestParam Long labelId){
        Map<String,Object> modelMap = new HashMap<>();
        if (labelService.deleteLabel(labelId).equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "移除标签失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 更新标签信息 标签不能重复
     * @CreateDate 14:44 2021/1/30
     * @UpdateDate 14:44 2021/1/30
     * @Param [label]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updateLabel", method = RequestMethod.POST)
    private Map<String,Object> updateLabel(@RequestBody Label label){
        Map<String,Object> modelMap = new HashMap<>();
        if (labelService.updateLabel(label).equals(1)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "更新标签信息失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取所有标签
     * @CreateDate 14:45 2021/1/30
     * @UpdateDate 14:45 2021/1/30
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getAllLabel", method = RequestMethod.POST)
    private Map<String,Object> getAllLabel(){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("labelList", labelService.getAllLabel());
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取登录用户使用的所有标签
     * @CreateDate 12:07 2021/2/6
     * @UpdateDate 12:07 2021/2/6
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getMyLabel", method = RequestMethod.POST)
    private Map<String,Object> getMyLabel(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("labelList", labelService.getMyLabel(user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
}
