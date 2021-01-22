import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.po.ArticleWithBLOBs;
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
}
