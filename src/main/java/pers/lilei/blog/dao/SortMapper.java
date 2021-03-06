package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.bean.Sort;
import pers.lilei.blog.bean.SortExample;
import pers.lilei.blog.bean.resultBean.SortResultBean;
import pers.lilei.blog.param.UserParam;

public interface SortMapper {
    long countByExample(SortExample example);

    int deleteByExample(SortExample example);

    int deleteByPrimaryKey(Long sortId);

    int insert(Sort record);

    int insertSelective(Sort record);

    List<Sort> selectByExample(SortExample example);

    Sort selectByPrimaryKey(Long sortId);

    Sort selectBySortName(String sortName);

    Sort selectBySortNameWithoutSortId(@Param("sortName") String sortName, @Param("sortId") Long sortId);

    List<Sort> selectAllSort();

    List<Sort> selectSortByArticleId(Long articleId);

    List<Sort> selectSortByDraftId(Long draftId);

    List<SortResultBean> selectSortByUserId(@Param("param")UserParam userParam);

    int updateByExampleSelective(@Param("record") Sort record, @Param("example") SortExample example);

    int updateByExample(@Param("record") Sort record, @Param("example") SortExample example);

    int updateByPrimaryKeySelective(Sort record);

    int updateByPrimaryKey(Sort record);
}
