package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.SortMapper;
import pers.lilei.blog.po.Sort;
import pers.lilei.blog.service.SortService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>分类服务实现类</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:17
 **/
@Service
public class SortServiceImpl implements SortService {
    SortMapper sortMapper;
    @Autowired
    public SortServiceImpl(SortMapper sortMapper) {
        this.sortMapper = sortMapper;
    }

    @Override
    public Integer addSort(Sort sort) {
        if (selectBySortName(sort.getSortName()) != null) {
            return -1;
        }
        return sortMapper.insertSelective(sort);
    }

    @Override
    public Integer deleteSort(Long sortId) {
        return sortMapper.deleteByPrimaryKey(sortId);
    }

    @Override
    public Integer updateSort(Sort sort) {
        if (selectBySortNameWithoutSortId(sort.getSortName(), sort.getSortId()) != null) {
            return -1;
        }
        return sortMapper.updateByPrimaryKeySelective(sort);
    }

    @Override
    public Sort selectBySortName(String sortName) {
        return sortMapper.selectBySortName(sortName);
    }

    @Override
    public Sort selectBySortNameWithoutSortId(String sortName, Long sortId) {
        return sortMapper.selectBySortNameWithoutSortId(sortName, sortId);
    }

    @Override
    public List<Sort> getAllSort() {
        return sortMapper.selectAllSort();
    }
}
