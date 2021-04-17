package pers.lilei.blog.service;

import pers.lilei.blog.bean.Sort;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>分类Service层</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:16
 **/
public interface SortService {
    Integer addSort(Sort sort);

    Integer deleteSort(Long sortId);

    Integer updateSort(Sort sort);

    Sort selectBySortName(String sortName);

    Sort selectBySortNameWithoutSortId(String sortName, Long sortId);

    List<Sort> getAllSort();

    List<Sort> getMySort(Long userId);
}
