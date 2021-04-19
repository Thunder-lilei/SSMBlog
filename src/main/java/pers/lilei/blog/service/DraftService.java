package pers.lilei.blog.service;

import org.springframework.stereotype.Service;
import pers.lilei.blog.bean.ArticleWithBLOBs;
import pers.lilei.blog.bean.DraftWithBLOBs;
import pers.lilei.blog.param.DraftParam;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>草稿</p>
 *
 * @author : 李雷
 * @date : 2021-04-19 11:40
 **/
public interface DraftService {
    List<DraftWithBLOBs> getUserDraft(DraftParam draftParam);

    DraftWithBLOBs selectByTitle(DraftParam draftParam);

    DraftWithBLOBs selectByDraftId(DraftParam draftParam);

    int addDraft(DraftWithBLOBs draftWithBLOBs);

    boolean ifOverNum(DraftParam draftParam);

    int updateDraft(DraftWithBLOBs draftWithBLOBs);

    int deleteDraft(DraftParam draftParam);

    int uploadDraft(DraftWithBLOBs draftWithBLOBs, ArticleWithBLOBs articleWithBLOBs);
}
