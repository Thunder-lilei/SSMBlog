import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.bean.Label;
import pers.lilei.blog.service.LabelService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>标签服务测试</p>
 *
 * @author : 李雷
 * @date : 2021-01-30 14:46
 **/
public class LabelServiceTest extends BaseTest{
    @Autowired
    LabelService labelService;
    @Test
    public void getAllLabel() {
        List<Label> labelList = labelService.getAllLabel();
        labelList.forEach(temp-> System.out.println(temp.getLabelName()));
    }
    @Test
    public void getMyLabel() {
        List<Label> labelList = labelService.getMyLabel(1L);
        labelList.forEach(temp-> System.out.println(temp.getLabelName()));
    }
}
