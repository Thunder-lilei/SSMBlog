package pers.lilei.blog.service;

import pers.lilei.blog.bean.ArticleLike;

/**
 * <h3>SSMBlog</h3>
 * <p>点赞服务层</p>
 *
 * @author : 李雷
 * @date : 2021-01-15 15:05
 **/
public interface LikeService {
    Integer addLike(ArticleLike articleLike);

    Integer removeLike(ArticleLike articleLike);

    Boolean ifHaveLike(ArticleLike articleLike);
}
