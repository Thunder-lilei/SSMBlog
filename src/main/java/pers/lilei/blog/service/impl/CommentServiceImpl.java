package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.CommentMapper;
import pers.lilei.blog.dao.UserMapper;
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
    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper, UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
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
