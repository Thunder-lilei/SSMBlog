package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.Comment;
import pers.lilei.blog.pojo.CommentWithUserBaseInfoPojo;
import pers.lilei.blog.service.CommentService;
import pers.lilei.blog.service.UserService;

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
    @ResponseBody
    @RequestMapping(value = "/getComment", method = RequestMethod.POST)
    private Map<String,Object> getComment(@RequestParam Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        List<CommentWithUserBaseInfoPojo> commentWithUserBaseInfoPojoList = commentService.getCommentByArticleId(articleId);
        if (!commentWithUserBaseInfoPojoList.isEmpty()) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("commentList", commentWithUserBaseInfoPojoList);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取评论失败！");
        }
        return modelMap;
    }
}
