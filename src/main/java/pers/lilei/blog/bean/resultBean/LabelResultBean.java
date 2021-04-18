package pers.lilei.blog.bean.resultBean;

import java.util.Date;

/**
 * <h3>SSMBlog</h3>
 * <p>标签查询对象</p>
 *
 * @author : 李雷
 * @date : 2021-04-18 14:17
 **/
public class LabelResultBean {
    private Long labelId;

    private String labelName;

    private String labelDescription;
    /**
     * @description 当前标签当前用户博文数量
     * @author lilei
     * @Time 2021/4/18
     * @updateTime 2021/4/18
     */
    private int num;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelDescription() {
        return labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
