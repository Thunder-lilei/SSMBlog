package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.ArticleSort;
import pers.lilei.blog.po.ArticleSortExample;

public interface ArticleSortMapper {
    long countByExample(ArticleSortExample example);

    int deleteByExample(ArticleSortExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleSort record);

    int insertSelective(ArticleSort record);

    List<ArticleSort> selectByExample(ArticleSortExample example);

    ArticleSort selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ArticleSort record, @Param("example") ArticleSortExample example);

    int updateByExample(@Param("record") ArticleSort record, @Param("example") ArticleSortExample example);

    int updateByPrimaryKeySelective(ArticleSort record);

    int updateByPrimaryKey(ArticleSort record);
}