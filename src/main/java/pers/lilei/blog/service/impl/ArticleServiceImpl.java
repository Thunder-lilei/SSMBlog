package pers.lilei.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.*;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.bean.resultBean.ArticleBaseInfoBean;
import pers.lilei.blog.param.*;
import pers.lilei.blog.service.ArticleLabelService;
import pers.lilei.blog.service.ArticleService;
import pers.lilei.blog.service.ArticleSortService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文Service层实现</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 12:18
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleMapper articleMapper;
    UserMapper userMapper;
    CommentMapper commentMapper;
    ArticleLabelMapper articleLabelMapper;
    ArticleSortMapper articleSortMapper;
    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, UserMapper userMapper, CommentMapper commentMapper, ArticleLabelMapper articleLabelMapper, ArticleSortMapper articleSortMapper) {
        this.articleMapper = articleMapper;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.articleLabelMapper = articleLabelMapper;
        this.articleSortMapper = articleSortMapper;
    }



    /*
     * @Author 李雷
     * @Description
     * 通过用户ID获取其所有博文
     * 获取博文关联的用户基本信息
     * @CreateDate 16:26 2021/1/2
     * @UpdateDate 16:26 2021/1/2
     * @Param []
     * @return java.util.List<pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo>
     **/
    @Override
    public PageInfo<ArticleWithUserBaseInfoParam> selectAllArticleWithUserBaseInfoByUserId(int pageNow, int pageSize, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleWithUserBaseInfoParam> articleWithUserBaseInfoParamList = articleMapper.selectAllArticleWithUserBaseInfoByUserId(userId);
        //获取用户基本信息
        for (ArticleWithUserBaseInfoParam articleWithUserBaseInfoParam : articleWithUserBaseInfoParamList) {
            articleWithUserBaseInfoParam.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(articleWithUserBaseInfoParam.getUserId()));
        }
        return new PageInfo<>(articleWithUserBaseInfoParamList);
    }

    /*
     * @Author 李雷
     * @Description
     * 通过关键词和用户ID获取所有博文
     * 获取博文关联的用户基本信息
     * @CreateDate 14:47 2021/1/5
     * @UpdateDate 14:47 2021/1/5
     * @Param [pageNow, pageSize, key]
     * @return com.github.pagehelper.PageInfo<pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo>
     **/
    @Override
    public PageInfo<ArticleWithUserBaseInfoParam> selectArticleWithUserBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleWithUserBaseInfoParam> articleWithUserBaseInfoParamList = articleMapper.selectArticleWithUserBaseInfoByUserIdAndKey(userId, key);
        //获取用户基本信息
        for (ArticleWithUserBaseInfoParam articleWithUserBaseInfoParam : articleWithUserBaseInfoParamList) {
            articleWithUserBaseInfoParam.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(articleWithUserBaseInfoParam.getUserId()));
        }
        return new PageInfo<>(articleWithUserBaseInfoParamList);
    }

    @Override
    public PageInfo<ArticleBaseInfoBean> selectAllArticleBaseInfoByUserId(int pageNow, int pageSize, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleMapper.selectAllArticleBaseInfoByUserId(userId);
        return new PageInfo<>(articleBaseInfoBeanList);
    }

    @Override
    public List<ArticleBaseInfoBean> selectUserArticleBaseInfo(PageUserParam pageUserParam) {
        return articleMapper.selectUserArticleBaseInfo(pageUserParam);
    }

    @Override
    public ArticleWithBLOBs selectArticleById(ArticleParam articleParam) {
        return articleMapper.selectByPrimaryKey(articleParam.getArticleId());
    }

    @Override
    public PageInfo<ArticleBaseInfoBean> selectArticleBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleMapper.selectArticleBaseInfoByUserIdAndKey(userId, key);
        return new PageInfo<>(articleBaseInfoBeanList);
    }

    @Override
    public PageInfo<ArticleBaseInfoBean> getSortAboutArticleWithUserId(int pageNow, int pageSize, Long sortId, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleMapper.selectSortAboutArticleWithUserId(sortId, userId);
        return new PageInfo<>(articleBaseInfoBeanList);
    }

    @Override
    public PageInfo<ArticleBaseInfoBean> getLabelAboutArticleWithUserId(int pageNow, int pageSize, Long labelId, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleMapper.selectLabelAboutArticleWithUserId(labelId, userId);
        return new PageInfo<>(articleBaseInfoBeanList);
    }

    @Override
    public PageInfo<ArticleBaseInfoBean> getSortAboutArticleWithUserIdAndKey(int pageNow, int pageSize, Long sortId, Long userId, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleMapper.selectSortAboutArticleWithUserIdAndKey(sortId, userId, key);
        return new PageInfo<>(articleBaseInfoBeanList);
    }

    @Override
    public PageInfo<ArticleBaseInfoBean> getLabelAboutArticleWithUserIdAndKey(int pageNow, int pageSize, Long labelId, Long userId, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleMapper.selectLabelAboutArticleWithUserIdAndKey(labelId, userId, key);
        return new PageInfo<>(articleBaseInfoBeanList);
    }

    @Override
    public PageInfo<ArticleWithUserBaseInfoParam> searchArticle(int pageNow, int pageSize, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleWithUserBaseInfoParam> articleWithUserBaseInfoParamList = articleMapper.selectArticleByKey(key);
        //获取用户基本信息
        for (ArticleWithUserBaseInfoParam articleWithUserBaseInfoParam : articleWithUserBaseInfoParamList) {
            articleWithUserBaseInfoParam.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(articleWithUserBaseInfoParam.getUserId()));
        }
        return new PageInfo<>(articleWithUserBaseInfoParamList);
    }

    @Override
    public ArticleWithBLOBs selectByArticleTitle(String title) {
        return articleMapper.selectByArticleTitle(title);
    }

    /*
     * @Author 李雷
     * @Description
     * 创建博文 标题不允许重复
     * @CreateDate 21:41 2021/2/1
     * @UpdateDate 21:41 2021/2/1
     * @Param [articleWithBLOBs]
     * @return java.lang.Integer
     **/
    @Override
    public Integer addArticle(ArticleWithBLOBs articleWithBLOBs) {
        if (selectByArticleTitle(articleWithBLOBs.getArticleTitle()) != null) {
            return -1;
        }
        return articleMapper.insertSelective(articleWithBLOBs);
    }

    @Override
    public Integer deleteArticleByArticleId(Long articleId) {
        ArticleParam articleParam = new ArticleParam();
        articleParam.setArticleId(articleId);
        //移除关联的所有标签
        if (articleLabelMapper.deleteAllArticleLabel(articleParam) < 0) {
            return -1;
        }
        //移除关联的所有分类
        if (articleSortMapper.deleteAllArticleSort(articleParam) < 0) {
            return -1;
        }
        return articleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public Integer updateArticle(ArticleWithBLOBs articleWithBLOBs) {
        return articleMapper.updateByPrimaryKeySelective(articleWithBLOBs);
    }

    @Override
    public ArticleWithBLOBs getArticleByArticleId(Long articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    /*
     * @Author 李雷
     * @Description
     * 通过点赞数和评论总数获取排名靠前的一定数量的博文
     * 获取博文关联的用户基本信息
     * @CreateDate 13:55 2021/1/8
     * @UpdateDate 13:55 2021/1/8
     * @Param [size]
     * @return java.util.List<pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo>
     **/
    @Override
    public List<ArticleWithUserBaseInfoParam> getRecommendArticle(int size) {
        List<ArticleWithUserBaseInfoParam> articleWithUserBaseInfoParamList = articleMapper.getRecommendArticle(size);
        //获取用户基本信息
        for (ArticleWithUserBaseInfoParam articleWithUserBaseInfoParam : articleWithUserBaseInfoParamList) {
            articleWithUserBaseInfoParam.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(articleWithUserBaseInfoParam.getUserId()));
        }
        return articleWithUserBaseInfoParamList;
    }

    @Override
    public List<RecommendUserParam> getRecommendUser(int size) {
        List<RecommendUserParam> recommendUserParamList = articleMapper.getRecommendUser(size);
        //获取用户基本信息
        for (RecommendUserParam recommendUserParam : recommendUserParamList) {
            recommendUserParam.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(recommendUserParam.getUserId()));
        }
        return recommendUserParamList;
    }

    @Override
    public Long getUserIdByArticleId(Long articleId) {
        return articleMapper.selectUserIdByArticleId(articleId);
    }

    @Override
    public int getArticleCommentNum(ArticleParam articleParam) {
        return commentMapper.selectArticleCommentNum(articleParam);
    }

    @Override
    public List<ArticleBaseInfoBean> getArticleByUserOrder(UserParam userParam, PageParam pageParam) {
        return articleMapper.getArticleByUserOrder(userParam, pageParam);
    }

    @Override
    public int getArticleByUserOrderCount(UserParam userParam) {
        return articleMapper.getArticleByUserOrderCount(userParam);
    }

    @Override
    public List<ArticleBaseInfoBean> getNewArticleByUser(UserParam userParam, int num) {
        return articleMapper.getNewArticleByUser(userParam, num);
    }
}
