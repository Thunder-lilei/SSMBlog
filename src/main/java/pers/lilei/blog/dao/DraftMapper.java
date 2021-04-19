package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.bean.Draft;
import pers.lilei.blog.bean.DraftExample;
import pers.lilei.blog.bean.DraftWithBLOBs;
import pers.lilei.blog.param.DraftParam;

public interface DraftMapper {
    long countByExample(DraftExample example);

    int deleteByExample(DraftExample example);

    int deleteByPrimaryKey(Long draftId);

    int deleteDraft(@Param("param")DraftParam draftParam);

    int insert(DraftWithBLOBs record);

    int insertSelective(DraftWithBLOBs record);

    List<DraftWithBLOBs> selectByExampleWithBLOBs(DraftExample example);

    List<DraftWithBLOBs> selectUserDraft(@Param("param") DraftParam draftParam);

    int selectUserDraftCount(@Param("param") DraftParam draftParam);

    List<Draft> selectByExample(DraftExample example);

    DraftWithBLOBs selectByPrimaryKey(Long draftId);

    DraftWithBLOBs selectByTitle(@Param("param")DraftParam draftParam);

    int updateByExampleSelective(@Param("record") DraftWithBLOBs record, @Param("example") DraftExample example);

    int updateByExampleWithBLOBs(@Param("record") DraftWithBLOBs record, @Param("example") DraftExample example);

    int updateByExample(@Param("record") Draft record, @Param("example") DraftExample example);

    int updateByPrimaryKeySelective(DraftWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DraftWithBLOBs record);

    int updateByPrimaryKey(Draft record);

}
