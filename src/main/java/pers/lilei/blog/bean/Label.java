package pers.lilei.blog.bean;

import java.util.Date;
import java.util.Objects;

public class Label {
    private Long labelId;

    private String labelName;

    private Date createTime;

    private Date updateTime;

    private String labelDescription;

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
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLabelDescription() {
        return labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription == null ? null : labelDescription.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return labelId.equals(label.labelId) &&
                Objects.equals(labelName, label.labelName) &&
                Objects.equals(labelDescription, label.labelDescription) &&
                Objects.equals(createTime, label.createTime) &&
                Objects.equals(updateTime, label.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelId, labelDescription, labelName, createTime, updateTime);
    }
}
