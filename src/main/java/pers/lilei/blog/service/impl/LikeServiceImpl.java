package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.ArticleLikeMapper;
import pers.lilei.blog.dao.ArticleMapper;
import pers.lilei.blog.po.ArticleLike;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.service.LikeService;

/**
 * <h3>SSMBlog</h3>
 * <p>点赞服务层实现类</p>
 *
 * @author : 李雷
 * @date : 2021-01-15 15:05
 **/
@Service
public class LikeServiceImpl implements LikeService {
    ArticleLikeMapper articleLikeMapper;
    ArticleMapper articleMapper;
    @Autowired
    public LikeServiceImpl(ArticleLikeMapper articleLikeMapper, ArticleMapper articleMapper) {
        this.articleLikeMapper = articleLikeMapper;
        this.articleMapper = articleMapper;
    }


    /*
     * @Author 李雷
     * @Description
     * 博文点赞 不允许重复点赞
     * 增加博文的总点赞数量
     * @CreateDate 15:33 2021/1/15
     * @UpdateDate 15:33 2021/1/15
     * @Param [articleLike]
     * @return java.lang.Integer
     **/
    @Override
    public Integer addLike(ArticleLike articleLike) {
        if (ifHaveLike(articleLike)) {
            return 0;
        }
        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(articleLike.getArticleId());
        if (articleWithBLOBs != null) {
            //点赞数加一
            articleWithBLOBs.setArticleLikeCount(articleWithBLOBs.getArticleLikeCount() + 1);
            if (articleMapper.updateByPrimaryKeySelective(articleWithBLOBs) == 0) {
                return 0;
            }
        } else {
            return 0;
        }
        return articleLikeMapper.insertSelective(articleLike);
    }

    /*
     * @Author 李雷
     * @Description
     * 取消点赞
     * 点赞数减一
     * @CreateDate 16:47 2021/1/15
     * @UpdateDate 16:47 2021/1/15
     * @Param [articleLike]
     * @return java.lang.Integer
     **/
    @Override
    public Integer removeLike(ArticleLike articleLike) {
        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(articleLike.getArticleId());
        if (articleWithBLOBs != null) {
            //点赞数加一
            articleWithBLOBs.setArticleLikeCount(articleWithBLOBs.getArticleLikeCount() - 1);
            if (articleMapper.updateByPrimaryKeySelective(articleWithBLOBs) == 0) {
                return 0;
            }
        } else {
            return 0;
        }
        return articleLikeMapper.deleteByArticleIdAndUserId(articleLike.getArticleId(), articleLike.getUserId());
    }

    @Override
    public Boolean ifHaveLike(ArticleLike articleLike) {
        return articleLikeMapper.selectByArticleIdAndUserId(articleLike.getArticleId(), articleLike.getUserId()) != null;
    }
}
