import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.bean.Sort;
import pers.lilei.blog.service.ArticleSortService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文分类服务类测试</p>
 *
 * @author : 李雷
 * @date : 2021-01-31 13:21
 **/
public class ArticleSortServiceTest extends BaseTest{
    @Autowired
    ArticleSortService articleSortService;
    @Test
    public void getAllSort() {
        List<Sort> sortList = articleSortService.getAllArticleSort(1L);
        sortList.forEach(temp-> System.out.println(temp.getSortName()));
    }
}
