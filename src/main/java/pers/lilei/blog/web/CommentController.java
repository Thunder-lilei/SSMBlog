package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.bean.Comment;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.param.CommentWithUserBaseInfoParam;
import pers.lilei.blog.service.CommentService;
import pers.lilei.blog.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>评论Control层</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:14
 **/
@Controller
@RequestMapping(value = "/comment")
public class CommentController extends BaseController{
    CommentService commentService;
    UserService userService;
    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取所有评论
     * @CreateDate 0:37 2021/1/18
     * @UpdateDate 0:37 2021/1/18
     * @Param [articleId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getComment", method = RequestMethod.POST)
    private Map<String,Object> getComment(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        List<CommentWithUserBaseInfoParam> commentWithUserBaseInfoParamList = commentService.getCommentByArticleId(articleId);
        if (!commentWithUserBaseInfoParamList.isEmpty()) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("commentList", commentWithUserBaseInfoParamList);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取评论失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 添加评论
     * @CreateDate 0:41 2021/1/18
     * @UpdateDate 0:41 2021/1/18
     * @Param [articleId, commentId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    private Map<String,Object> addComment(@RequestBody Comment comment){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setUserId(user.getUserId());
            comment.setCommentDate(new Date());
            if (!commentService.addComment(comment).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "评论失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 移除评论
     * @CreateDate 0:46 2021/1/18
     * @UpdateDate 0:46 2021/1/18
     * @Param [commentId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    private Map<String,Object> deleteComment(@RequestParam Long commentId){
        Map<String,Object> modelMap = new HashMap<>();
        if (!commentService.deleteComment(commentId).equals(0)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "移除失败！");
        }
        return modelMap;
    }
}
