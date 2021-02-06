package pers.lilei.blog.service;

import pers.lilei.blog.po.Label;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>标签Service层</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:15
 **/
public interface LabelService {
    Integer addLabel(Label label);

    Integer deleteLabel(Long labelId);

    Integer updateLabel(Label label);

    Label selectByLabelName(String labelName);

    Label selectByLabelNameWithoutLabelId(String labelName, Long labelId);

    List<Label> getAllLabel();

    List<Label> getMyLabel(Long userId);
}
