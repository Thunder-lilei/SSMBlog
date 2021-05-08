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
    /**
     * @description 关键词分页查询用户分类博文
     * @author lilei
     * @Time 2021/5/9
     * @updateTime 2021/5/9
     */
    List<ArticleBaseInfoBean> getSortLabelAboutArticleWithUserIdAndKey(PageSortLabelKeyParam pageSortLabelKeyParam, UserParam userParam);
    /**
     * @description 统计关键词分页查询用户分类博文数量
     * @author lilei
     * @Time 2021/5/9
     * @updateTime 2021/5/9
     */
    int countSortLabelAboutArticleWithUserIdAndKey(PageSortLabelKeyParam pageSortLabelKeyParam, UserParam userParam);
    /**
     * @description 关键词分页查询用户博文列表
     * @author lilei
     * @Time 2021/5/8
     * @updateTime 2021/5/8
     */
    List<ArticleBaseInfoBean> selectUserArticleBaseInfoWithKey(PageKeyParam pageKeyParam, UserParam userParam);
    /**
     * @description 统计关键词查询用户博文列表数量
     * @author lilei
     * @Time 2021/5/8
     * @updateTime 2021/5/8
     */
    int countUserArticleBaseInfoWithKey(PageKeyParam pageKeyParam, UserParam userParam);
    /**
     * @description 查询博文
     * @author lilei
     * @Time 2021/4/24
     * @updateTime 2021/4/24
     */
    ArticleWithBLOBs selectArticleById(ArticleParam articleParam);

}
