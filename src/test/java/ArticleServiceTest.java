import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.po.ArticleWithBLOBs;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.pojo.ArticleWithUserBaseInfoPojo;
import pers.lilei.blog.pojo.RecommendUserPojo;
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
        PageInfo<ArticleWithUserBaseInfoPojo> articleWithUserBaseInfoPojoPageInfo = articleService.selectAllArticleWithUserBaseInfoByUserId(1, 10, 1L);
        System.out.println(articleWithUserBaseInfoPojoPageInfo);
    }
//    @Test
//    public void selectByKey() {
//        PageInfo<ArticleWithUserBaseInfoPojo> articleWithUserBaseInfoPojoPageInfo = articleService.selectArticleBaseInfoByKey(1, 10, 1L, "第");
//        System.out.println(articleWithUserBaseInfoPojoPageInfo);
//    }
    @Test
    public void getCommendArticle() {
        List<ArticleWithUserBaseInfoPojo> articleWithUserBaseInfoPojoList = articleService.getRecommendArticle(5);
        articleWithUserBaseInfoPojoList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
    @Test
    public void getCommendUser() {
        List<RecommendUserPojo> recommendUserPojoList = articleService.getRecommendUser(5);
        recommendUserPojoList.forEach(temp-> System.out.println(temp.getUserBaseInfoPojo().getUserNickname()));
        recommendUserPojoList.forEach(temp-> System.out.println(temp.getSumViews()));
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
}
