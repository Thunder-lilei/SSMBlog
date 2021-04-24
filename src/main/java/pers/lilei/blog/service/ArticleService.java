package pers.lilei.blog.service;

import com.github.pagehelper.PageInfo;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.bean.resultBean.ArticleBaseInfoBean;
import pers.lilei.blog.param.*;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文Service层</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 12:17
 **/
public interface ArticleService {
    PageInfo<ArticleWithUserBaseInfoParam> selectAllArticleWithUserBaseInfoByUserId(int pageNow, int pageSize, Long userId);

    PageInfo<ArticleWithUserBaseInfoParam> selectArticleWithUserBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key);

    PageInfo<ArticleBaseInfoBean> selectAllArticleBaseInfoByUserId(int pageNow, int pageSize, Long userId);

    PageInfo<ArticleBaseInfoBean> selectArticleBaseInfoByUserIdAndKey(int pageNow, int pageSize, Long userId, String key);

    PageInfo<ArticleBaseInfoBean> getSortAboutArticleWithUserId(int pageNow, int pageSize, Long sortId, Long userId);

    PageInfo<ArticleBaseInfoBean> getLabelAboutArticleWithUserId(int pageNow, int pageSize, Long labelId, Long userId);

    PageInfo<ArticleBaseInfoBean> getSortAboutArticleWithUserIdAndKey(int pageNow, int pageSize, Long sortId, Long userId, String key);

    PageInfo<ArticleBaseInfoBean> getLabelAboutArticleWithUserIdAndKey(int pageNow, int pageSize, Long labelId, Long userId, String key);

    PageInfo<ArticleWithUserBaseInfoParam> searchArticle(int pageNow, int pageSize, String key);

    ArticleWithBLOBs selectByArticleTitle(String title);

    Integer addArticle(ArticleWithBLOBs articleWithBLOBs);

    Integer deleteArticleByArticleId(Long articleId);

    Integer updateArticle(ArticleWithBLOBs articleWithBLOBs);

    ArticleWithBLOBs getArticleByArticleId(Long articleId);

    List<ArticleWithUserBaseInfoParam> getRecommendArticle(int size);

    List<RecommendUserParam> getRecommendUser(int size);

    Long getUserIdByArticleId(Long articleId);

    int getArticleCommentNum(ArticleParam articleParam);

    List<ArticleBaseInfoBean> getArticleByUserOrder(UserParam userParam, PageParam pageParam);

    /**
     * @description 获取用户的博文总数
     * @author lilei
     */
    int getArticleByUserOrderCount(UserParam userParam);
    /**
     * @description 获取用户的最新博文
     * @author lilei
     * @Time 2021/4/24
     * @updateTime 2021/4/24
     */
    List<ArticleBaseInfoBean> getNewArticleByUser(UserParam userParam, int num);
    /**
     * @description 分页查询用户博文列表
     * @author lilei
     * @Time 2021/4/24
     * @updateTime 2021/4/24
     */
    List<ArticleBaseInfoBean> selectUserArticleBaseInfo(PageUserParam pageUserParam);

}
