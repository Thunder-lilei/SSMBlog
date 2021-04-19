import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.bean.DraftWithBLOBs;
import pers.lilei.blog.constant.ReturnConstant;
import pers.lilei.blog.dao.DraftMapper;
import pers.lilei.blog.param.DraftParam;
import pers.lilei.blog.service.DraftService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p></p>
 *
 * @author : 李雷
 * @date : 2021-04-19 13:56
 **/
public class DraftServiceTest extends BaseTest{
    @Autowired
    DraftService draftService;
    @Autowired
    DraftMapper draftMapper;
    @Test
    public void getUserDraft() {
        DraftParam draftParam = new DraftParam();
        draftParam.setUserId(1L);
        List<DraftWithBLOBs> draftWithBLOBsList = draftService.getUserDraft(draftParam);
        draftWithBLOBsList.forEach(temp-> System.out.println(temp.getArticleTitle()));
    }
    @Test
    public void count() {
        DraftParam draftParam = new DraftParam();
        draftParam.setUserId(1L);
        System.out.println(draftMapper.selectUserDraftCount(draftParam));
        System.out.println(draftService.ifOverNum(draftParam));
    }
    @Test
    public void getDraft() {
        DraftParam draftParam = new DraftParam();
        draftParam.setDraftId(2L);
        DraftWithBLOBs draftWithBLOBs = draftService.selectByDraftId(draftParam);
        System.out.println(draftWithBLOBs.getArticleTitle());
    }
}
