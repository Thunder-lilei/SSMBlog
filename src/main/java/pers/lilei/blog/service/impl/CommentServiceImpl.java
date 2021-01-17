package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.ArticleMapper;
import pers.lilei.blog.dao.CommentMapper;
import pers.lilei.blog.dao.UserMapper;
import pers.lilei.blog.po.Article;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.po.Comment;
import pers.lilei.blog.pojo.CommentWithUserBaseInfoPojo;
import pers.lilei.blog.service.CommentService;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>评论服务实现类</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:17
 **/
@Service
public class CommentServiceImpl implements CommentService {
    CommentMapper commentMapper;
    UserMapper userMapper;
    ArticleMapper articleMapper;
    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper, UserMapper userMapper, ArticleMapper articleMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.articleMapper = articleMapper;
    }

    /*
     * @Author 李雷
     * @Description
     * 添加评论的同时博文评论数加一
     * @CreateDate 0:44 2021/1/18
     * @UpdateDate 0:44 2021/1/18
     * @Param [comment]
     * @return java.lang.Integer
     **/
    @Override
    public Integer addComment(Comment comment) {
        if (commentMapper.insertSelective(comment) != 0) {
            ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(comment.getArticleId());
            //博文评论数加一
            articleWithBLOBs.setArticleCommentCount(articleWithBLOBs.getArticleCommentCount() + 1);
            if (articleMapper.updateByPrimaryKeySelective(articleWithBLOBs) == 0) {
                return 0;
            }
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteComment(Long commentId) {
        return commentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public List<CommentWithUserBaseInfoPojo> getCommentByArticleId(Long articleId) {
        List<CommentWithUserBaseInfoPojo> commentWithUserBaseInfoPojoList = new ArrayList<>();
        //获取根节点
        List<CommentWithUserBaseInfoPojo> rootCommentWithUserBaseInfoPojoList = getRootCommentByArticleId(articleId);
        for (CommentWithUserBaseInfoPojo commentWithUserBaseInfoPojo : rootCommentWithUserBaseInfoPojoList) {
            //添加根节点
            commentWithUserBaseInfoPojoList.add(commentWithUserBaseInfoPojo);
            //获取子节点
            getChildCommentByArticleIdAndParentCommentId(commentWithUserBaseInfoPojoList, articleId, commentWithUserBaseInfoPojo.getCommentId());
        }
        return commentWithUserBaseInfoPojoList;
    }

    @Override
    public List<CommentWithUserBaseInfoPojo> getChildCommentByArticleIdAndParentCommentId(List<CommentWithUserBaseInfoPojo> parentCommentWithUserBaseInfoPojoList, Long articleId, Long parentCommentId) {
        List<CommentWithUserBaseInfoPojo> commentWithUserBaseInfoPojoList = commentMapper.getChildCommentByArticleIdAndParentCommentId(articleId, parentCommentId);
        //设置用户基本信息
        commentWithUserBaseInfoPojoList.forEach(temp->temp.setUserBaseInfo(userMapper.selectUserBaseInfoByPrimaryKey(temp.getUserId())));
        //设置回复评论用户基本信息
        commentWithUserBaseInfoPojoList.forEach(temp->temp.setParentCommentUserBaseInfo(userMapper.selectUserBaseInfoByPrimaryKey(commentMapper.selectUserIdByCommentId(temp.getParentCommentId()))));
        //添加节点
        for (CommentWithUserBaseInfoPojo commentWithUserBaseInfoPojo : commentWithUserBaseInfoPojoList) {
            //添加根节点
            parentCommentWithUserBaseInfoPojoList.add(commentWithUserBaseInfoPojo);
            //获取子节点
            getChildCommentByArticleIdAndParentCommentId(parentCommentWithUserBaseInfoPojoList, articleId, commentWithUserBaseInfoPojo.getCommentId());
        }
        return commentWithUserBaseInfoPojoList;
    }

    @Override
    public List<CommentWithUserBaseInfoPojo> getRootCommentByArticleId(Long articleId) {
        List<CommentWithUserBaseInfoPojo> commentWithUserBaseInfoPojoList = commentMapper.getRootCommentByArticleId(articleId);
        //设置用户基本信息
        commentWithUserBaseInfoPojoList.forEach(temp->temp.setUserBaseInfo(userMapper.selectUserBaseInfoByPrimaryKey(temp.getUserId())));
        //设置回复评论用户基本信息
        commentWithUserBaseInfoPojoList.forEach(temp->temp.setParentCommentUserBaseInfo(userMapper.selectUserBaseInfoByPrimaryKey(commentMapper.selectUserIdByCommentId(temp.getParentCommentId()))));
        return commentWithUserBaseInfoPojoList;
    }

}
