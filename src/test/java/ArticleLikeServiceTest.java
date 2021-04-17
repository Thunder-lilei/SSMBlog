import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.bean.ArticleLike;
import pers.lilei.blog.service.LikeService;

/**
 * <h3>SSMBlog</h3>
 * <p>博文点赞测试类</p>
 *
 * @author : 李雷
 * @date : 2021-01-15 15:22
 **/
public class ArticleLikeServiceTest extends BaseTest{
    @Autowired
    LikeService likeService;
    @Test
    public void ifHaveLike() {
        ArticleLike articleLike = new ArticleLike();
        articleLike.setUserId(1L);
        articleLike.setArticleId(1L);
        System.out.println(likeService.ifHaveLike(articleLike));
    }
    @Test
    public void addLike() {
        ArticleLike articleLike = new ArticleLike();
        articleLike.setArticleId(5L);
        articleLike.setUserId(1L);
        System.out.println(likeService.addLike(articleLike));
    }
}
