import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.Comment;
import pers.lilei.blog.pojo.CommentWithUserBaseInfoPojo;
import pers.lilei.blog.service.CommentService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>评论服务测试</p>
 *
 * @author : 李雷
 * @date : 2021-01-17 01:37
 **/
public class CommentServiceTest extends BaseTest{
    @Autowired
    CommentService commentService;
    @Test
    public void getRootCommentByArticleId() {
        List<CommentWithUserBaseInfoPojo> commentList = commentService.getRootCommentByArticleId(5L);
        if (!commentList.isEmpty()) {
            commentList.forEach(temp-> System.out.println(temp.getCommentContent()));
        } else {
            System.out.println("获取根评论失败！");
        }
    }
//    @Test
//    public void getChildComment() {
//        List<CommentWithUserBaseInfoPojo> commentList = commentService.getChildCommentByArticleIdAndParentCommentId(5L, 2L);
//        if (!commentList.isEmpty()) {
//            commentList.forEach(temp-> System.out.println(temp.getCommentContent()));
//        } else {
//            System.out.println("获取子评论失败！");
//        }
//    }
    @Test
    public void getCommentByArticleId() {
        List<CommentWithUserBaseInfoPojo> commentList = commentService.getCommentByArticleId(5L);
        if (!commentList.isEmpty()) {
            commentList.forEach(temp-> System.out.println(temp.getCommentContent()));
        } else {
            System.out.println("获取根评论失败！");
        }
    }
    @Test
    public void addComment() {
        Comment comment = new Comment();
        comment.setUserId(1L);
        comment.setArticleId(5L);
        comment.setParentCommentId(0L);
        comment.setCommentContent("真的不错！");
        commentService.addComment(comment);
    }
}
