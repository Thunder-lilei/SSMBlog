package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.LabelMapper;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.service.LabelService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>标签服务实现类</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:16
 **/
@Service
public class LabelServiceImpl implements LabelService {
    LabelMapper labelMapper;
    @Autowired
    public LabelServiceImpl(LabelMapper labelMapper) {
        this.labelMapper = labelMapper;
    }

    @Override
    public Integer addLabel(Label label) {
        if (selectByLabelName(label.getLabelName()) != null) {
            return -1;
        }
        return labelMapper.insertSelective(label);
    }

    @Override
    public Integer deleteLabel(Long labelId) {
        return labelMapper.deleteByPrimaryKey(labelId);
    }

    @Override
    public Integer updateLabel(Label label) {
        if (selectByLabelNameWithoutLabelId(label.getLabelName(), label.getLabelId()) != null) {
            return -1;
        }
        return labelMapper.updateByPrimaryKeySelective(label);
    }

    @Override
    public Label selectByLabelName(String labelName) {
        return labelMapper.selectByLabelName(labelName);
    }

    @Override
    public Label selectByLabelNameWithoutLabelId(String labelName, Long labelId) {
        return labelMapper.selectByLabelNameWithoutLabelId(labelName, labelId);
    }

    @Override
    public List<Label> getAllLabel() {
        return labelMapper.selectAllLabel();
    }

    @Override
    public List<Label> getMyLabel(Long userId) {
        return labelMapper.selectLabelByUserId(userId);
    }
}
