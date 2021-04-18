import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.bean.Sort;
import pers.lilei.blog.bean.resultBean.LabelResultBean;
import pers.lilei.blog.bean.resultBean.SortResultBean;
import pers.lilei.blog.dao.SortMapper;
import pers.lilei.blog.param.UserParam;
import pers.lilei.blog.service.SortService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>分类服务测试</p>
 *
 * @author : 李雷
 * @date : 2021-01-30 11:52
 **/
public class SortServiceTest extends BaseTest{
    @Autowired
    SortService sortService;
    @Autowired
    SortMapper sortMapper;
    @Test
    public void addSort() {
        Sort sort = new Sort();
        sort.setSortName("数学");
        sort.setSortDescription("数学学科");
        sortService.addSort(sort);
    }
    @Test
    public void getAllSort(){
        List<Sort> sortList = sortService.getAllSort();
        sortList.forEach(temp-> System.out.println(temp.getSortName()));
    }
    @Test
    public void selectSortByUserId() {
        UserParam userParam = new UserParam();
        userParam.setUserId(1L);
        List<SortResultBean> sortResultBeanList = sortMapper.selectSortByUserId(userParam);
        sortResultBeanList.forEach(temp-> System.out.println(temp.getSortId()+" "+temp.getNum()+" "+temp.getSortName()));
    }
}
