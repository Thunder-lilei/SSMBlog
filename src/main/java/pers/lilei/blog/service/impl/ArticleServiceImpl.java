package pers.lilei.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.ArticleMapper;
import pers.lilei.blog.dao.UserMapper;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.po.User;
import pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo;
import pers.lilei.blog.service.ArticleService;

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
    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, UserMapper userMapper) {
        this.articleMapper = articleMapper;
        this.userMapper = userMapper;
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
    public PageInfo<ArticleWithUserBaseInfoPojo> selectAllArticleWithUserBaseInfoByUserId(int pageNow, int pageSize, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleWithUserBaseInfoPojo> articleWithUserBaseInfoPojoList = articleMapper.selectAllArticleWithUserBaseInfoByUserId(userId);
        //获取用户基本信息
        for (ArticleWithUserBaseInfoPojo articleWithUserBaseInfoPojo : articleWithUserBaseInfoPojoList) {
            articleWithUserBaseInfoPojo.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(articleWithUserBaseInfoPojo.getUserId()));
        }
        return new PageInfo<>(articleWithUserBaseInfoPojoList);
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
    public PageInfo<ArticleWithUserBaseInfoPojo> selectArticleBaseInfoByKey(int pageNow, int pageSize, Long userId, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<ArticleWithUserBaseInfoPojo> articleWithUserBaseInfoPojoList = articleMapper.selectArticleBaseInfoByKey(userId, key);
        //获取用户基本信息
        for (ArticleWithUserBaseInfoPojo articleWithUserBaseInfoPojo : articleWithUserBaseInfoPojoList) {
            articleWithUserBaseInfoPojo.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(articleWithUserBaseInfoPojo.getUserId()));
        }
        return new PageInfo<>(articleWithUserBaseInfoPojoList);
    }

    @Override
    public Integer addArticle(ArticleWithBLOBs articleWithBLOBs) {
        return articleMapper.insertSelective(articleWithBLOBs);
    }

    @Override
    public Integer deleteArticleByArticleId(Long articleId) {
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
    public List<ArticleWithUserBaseInfoPojo> getRecommendArticle(int size) {
        List<ArticleWithUserBaseInfoPojo> articleWithUserBaseInfoPojoList = articleMapper.getRecommendArticle(size);
        //获取用户基本信息
        for (ArticleWithUserBaseInfoPojo articleWithUserBaseInfoPojo : articleWithUserBaseInfoPojoList) {
            articleWithUserBaseInfoPojo.setUserBaseInfoPojo(userMapper.selectUserBaseInfoByPrimaryKey(articleWithUserBaseInfoPojo.getUserId()));
        }
        return articleWithUserBaseInfoPojoList;
    }
}
