package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.bean.ArticleLike;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.service.LikeService;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>点赞控制层</p>
 *
 * @author : 李雷
 * @date : 2021-01-15 15:04
 **/
@Controller
@RequestMapping(value = "/like")
public class LikeController extends BaseController{
    LikeService likeService;
    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    /*
     * @Author 李雷
     * @Description
     * 博文点赞
     * 不允许重复点赞
     * @CreateDate 15:33 2021/1/15
     * @UpdateDate 15:33 2021/1/15
     * @Param [articleLike]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addLike", method = RequestMethod.POST)
    private Map<String,Object> addLike(@RequestBody ArticleLike articleLike){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            articleLike.setUserId(user.getUserId());
            if (!likeService.addLike(articleLike).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "点过赞了！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 取消点赞
     * @CreateDate 15:46 2021/1/15
     * @UpdateDate 15:46 2021/1/15
     * @Param [articleLike]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/removeLike", method = RequestMethod.POST)
    private Map<String,Object> removeLike(@RequestBody ArticleLike articleLike){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            articleLike.setUserId(user.getUserId());
            if (!likeService.removeLike(articleLike).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "您没给点赞！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 检查是否已经给博文点赞
     * @CreateDate 15:33 2021/1/15
     * @UpdateDate 15:33 2021/1/15
     * @Param [articleLike]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/ifHaveLike", method = RequestMethod.POST)
    private Map<String,Object> ifHaveLike(@RequestBody ArticleLike articleLike){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            articleLike.setUserId(user.getUserId());
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("ifHaveLikeResult", likeService.ifHaveLike(articleLike));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }

}
