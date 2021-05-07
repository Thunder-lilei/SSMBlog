import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import pers.lilei.blog.bean.Comment;
import pers.lilei.blog.dao.CommentMapper;
import pers.lilei.blog.param.ArticleParam;
import pers.lilei.blog.param.CommentParam;
import pers.lilei.blog.param.CommentWithUserBaseInfoParam;
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
    @Autowired
    CommentMapper commentMapper;
    @Test
    public void getRootCommentByArticleId() {
        List<CommentWithUserBaseInfoParam> commentList = commentService.getRootCommentByArticleId(5L);
        if (!commentList.isEmpty()) {
            commentList.forEach(temp-> System.out.println(temp.getCommentContent()));
        } else {
            System.out.println("获取根评论失败！");
        }
    }
    @Test
    public void getChildComment() {
        List<CommentWithUserBaseInfoParam> commentWithUserBaseInfoParamList =
                commentMapper.getChildCommentByArticleIdAndParentCommentId(5L, 3L);
        if (!commentWithUserBaseInfoParamList.isEmpty()) {
            commentWithUserBaseInfoParamList.forEach(temp-> System.out.println(temp.getCommentContent()));
        } else {
            System.out.println("获取子评论失败！");
        }
    }
    @Test
    public void getCommentByArticleId() {
        List<CommentWithUserBaseInfoParam> commentList = commentService.getCommentByArticleId(5L);
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

    @Test
    public void deleteComment() {
        CommentParam commentParam = new CommentParam();
        commentParam.setCommentId(23L);
        System.out.println(commentService.deleteComment(commentParam));
    }

}
