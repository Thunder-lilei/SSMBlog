import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.bean.ArticleLabel;
import pers.lilei.blog.bean.Label;
import pers.lilei.blog.service.ArticleLabelService;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>博文标签服务类测试</p>
 *
 * @author : 李雷
 * @date : 2021-01-31 13:15
 **/
public class ArticleLabelServiceTest extends BaseTest{
    @Autowired
    ArticleLabelService articleLabelService;
    @Test
    public void getAllArticleLabel() {
        List<Label> labelList = articleLabelService.getAllArticleLabel(1L);
        labelList.forEach(temp-> System.out.println(temp.getLabelName()));
    }
    @Test
    public void selectByArticleIdAndLabelId() {
        ArticleLabel articleLabel = articleLabelService.selectByArticleIdAndLabelId(5L, 18L);
        System.out.println(articleLabel.getLabelId());
    }
    @Test
    public void containsTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(!list.contains(2));
    }
}
