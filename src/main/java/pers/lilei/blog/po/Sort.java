package pers.lilei.blog.po;

import java.util.Date;
import java.util.Objects;

public class Sort {
    private Long sortId;

    private String sortName;

    private String sortDescription;

    private Long parentSortId;

    private Date createTime;

    private Date updateTime;

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName == null ? null : sortName.trim();
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription == null ? null : sortDescription.trim();
    }

    public Long getParentSortId() {
        return parentSortId;
    }

    public void setParentSortId(Long parentSortId) {
        this.parentSortId = parentSortId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sort sort = (Sort) o;
        return sortId.equals(sort.sortId) &&
                Objects.equals(sortName, sort.sortName) &&
                Objects.equals(sortDescription, sort.sortDescription) &&
                Objects.equals(createTime, sort.createTime) &&
                Objects.equals(updateTime, sort.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortId, sortDescription, sortName, createTime, updateTime);
    }
}
