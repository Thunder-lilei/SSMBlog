package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import pers.lilei.blog.bean.Label;
import pers.lilei.blog.bean.LabelExample;
import pers.lilei.blog.bean.resultBean.LabelResultBean;
import pers.lilei.blog.param.UserParam;

public interface LabelMapper {
    long countByExample(LabelExample example);

    int deleteByExample(LabelExample example);

    int deleteByPrimaryKey(Long labelId);

    int insert(Label record);

    int insertSelective(Label record);

    List<Label> selectByExampleWithBLOBs(LabelExample example);

    List<Label> selectByExample(LabelExample example);

    Label selectByPrimaryKey(Long labelId);

    Label selectByLabelName(String labelName);

    List<Label> selectAllLabel();

    List<LabelResultBean> selectLabelByUserId(@Param("param")UserParam userParam);

    List<Label> selectLabelByArticleId(Long articleId);

    List<Label> selectLabelByDraftId(Long draftId);

    Label selectByLabelNameWithoutLabelId(@Param("labelName") String labelName, @Param("labelId") Long labelId);

    int updateByExampleSelective(@Param("record") Label record, @Param("example") LabelExample example);

    int updateByExampleWithBLOBs(@Param("record") Label record, @Param("example") LabelExample example);

    int updateByExample(@Param("record") Label record, @Param("example") LabelExample example);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKeyWithBLOBs(Label record);

    int updateByPrimaryKey(Label record);
}
