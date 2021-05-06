import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.bean.Label;
import pers.lilei.blog.bean.resultBean.ArticleBaseInfoBean;
import pers.lilei.blog.constant.ArticleConstant;
import pers.lilei.blog.param.*;
import pers.lilei.blog.service.ArticleService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文测试</p>
 *
 * @author : 李雷
 * @date : 2021-01-02 15:11
 **/
public class ArticleServiceTest extends BaseTest{
    @Autowired
    ArticleService articleService;
    @Test
    public void selectAllArticleWithUserBaseInfo() {
        PageInfo<ArticleWithUserBaseInfoParam> articleWithUserBaseInfoPojoPageInfo = articleService.selectAllArticleWithUserBaseInfoByUserId(1, 10, 1L);
        System.out.println(articleWithUserBaseInfoPojoPageInfo);
    }
//    @Test
//    public void selectByKey() {
//        PageInfo<ArticleWithUserBaseInfoPojo> articleWithUserBaseInfoPojoPageInfo = articleService.selectArticleBaseInfoByKey(1, 10, 1L, "第");
//        System.out.println(articleWithUserBaseInfoPojoPageInfo);
//    }
    @Test
    public void getCommendArticle() {
        List<ArticleWithUserBaseInfoParam> articleWithUserBaseInfoParamList = articleService.getRecommendArticle(5);
        articleWithUserBaseInfoParamList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
    @Test
    public void getCommendUser() {
        List<RecommendUserParam> recommendUserParamList = articleService.getRecommendUser(5);
        recommendUserParamList.forEach(temp-> System.out.println(temp.getUserBaseInfoPojo().getUserNickname()));
        recommendUserParamList.forEach(temp-> System.out.println(temp.getSumViews()));
    }
    @Test
    public void jsonTest() {
        String str1 = "[{\"labelId\":1,\"labelName\":\"语文\",\"createTime\":1611988059000,\"updateTime\":1611988059000,\"labelDescription\":\"语文学科\"}]";
        List<Label> labelList = JSON.parseObject(str1,new TypeReference<List<Label>>(){});
        labelList.forEach(temp-> System.out.println(temp.getLabelName()));
    }
//    @Test
//    public void jsonTest2() {
//        String str1 = "[{\"labelId\":1,\"labelName\":\"语文\",\"createTime\":1611988059000,\"updateTime\":1611988059000,\"labelDescription\":\"语文学科\"}]";
//        List<Label> labelList = JsonUtil.jsonToList(str1, Label.class);
//        labelList.forEach(temp-> System.out.println(temp.getLabelName()));
//    }
    @Test
    public void getJsonArticle() {
        String jsonArticle = "{\"articleId\":5,\"userId\":1,\"articleViews\":122,\"articleCommentCount\":14,\"articleDate\":\"2021-01-05\",\"articleLikeCount\":6,\"createTime\":1609824412000,\"updateTime\":1610903417000,\"articleTitle\":\"第一篇博文\",\"articleContent\":\"#  这是第一篇博文\\n就随便写一点内容吧\\n再加一点内容\\n-  第一天\\n-  第二天\\n-  第三天\\n![image](https://img-blog.csdnimg.cn/20200708235625588.jpg)\"}\n";
        ArticleWithBLOBs articleWithBLOBs = JSON.parseObject(jsonArticle, ArticleWithBLOBs.class);
        System.out.println(articleWithBLOBs);
    }
    @Test
    public void getSortAboutArticle() {
        PageInfo<ArticleBaseInfoBean> articleWithBLOBsPageInfo = articleService.getSortAboutArticleWithUserId(1, 10, 3L, 1L);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleWithBLOBsPageInfo.getList();
        articleBaseInfoBeanList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
    @Test
    public void getLabelAboutArticle() {
        PageInfo<ArticleBaseInfoBean> articleWithBLOBsPageInfo = articleService.getLabelAboutArticleWithUserId(1, 10, 3L, 1L);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleWithBLOBsPageInfo.getList();
        articleBaseInfoBeanList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
    @Test
    public void getSortAboutArticleAndKey() {
        PageInfo<ArticleBaseInfoBean> articleWithBLOBsPageInfo = articleService.getSortAboutArticleWithUserIdAndKey(1, 10, 3L, 1L, "一");
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleWithBLOBsPageInfo.getList();
        articleBaseInfoBeanList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
    @Test
    public void searchArticle() {
        PageInfo<ArticleWithUserBaseInfoParam> articleWithBLOBsPageInfo = articleService.searchArticle(1, 10, "一");
        List<ArticleWithUserBaseInfoParam> articleBaseInfoPojoList = articleWithBLOBsPageInfo.getList();
        articleBaseInfoPojoList.forEach(temp-> System.out.println(temp.getArticleTitle()));
        articleBaseInfoPojoList.forEach(temp-> System.out.println(temp.getUserBaseInfoPojo().getUserNickname()));
    }
    @Test
    public void getUserIdByArticleId() {
        System.out.println(articleService.getUserIdByArticleId(5L));
    }

    @Test
    public void getArticleCommentNum() {
        ArticleParam articleParam = new ArticleParam();
        articleParam.setArticleId(5L);
        System.out.println(articleService.getArticleCommentNum(articleParam));
    }

    @Test
    public void getArticleByUserOrder() {
        UserParam userParam = new UserParam();
        userParam.setUserId(1L);
        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(0);
        pageParam.setPageSize(5);
        System.out.println("总数："+articleService.getArticleByUserOrderCount(userParam));
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleService.getArticleByUserOrder(userParam, pageParam);
        articleBaseInfoBeanList.forEach(temp-> System.out.println(temp.getArticleId()));
    }
    @Test
    public void getNewArticleByUser() {
        UserParam userParam = new UserParam();
        userParam.setUserId(1L);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleService.getNewArticleByUser(userParam, ArticleConstant.NEW_ARTICLE_NUM);
        articleBaseInfoBeanList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
    @Test
    public void selectUserArticleBaseInfo() {
        PageUserParam pageUserParam = new PageUserParam();
        PageParam pageParam = new PageParam();
        UserParam userParam = new UserParam();
        pageUserParam.setPageParam(pageParam);
        pageUserParam.setUserParam(userParam);
        pageUserParam.getPageParam().setPageIndex(5);
        pageUserParam.getPageParam().setPageSize(5);
        pageUserParam.getUserParam().setUserId(1L);
        List<ArticleBaseInfoBean> articleBaseInfoBeanList = articleService.selectUserArticleBaseInfo(pageUserParam);
        articleBaseInfoBeanList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
}
