package pers.lilei.blog.bean.resultBean;

import java.util.Date;

/**
 * <h3>SSMBlog</h3>
 * <p>分类查询对象</p>
 *
 * @author : 李雷
 * @date : 2021-04-18 14:40
 **/
public class SortResultBean {
    private Long sortId;

    private String sortName;

    private String sortDescription;

    private Long parentSortId;
    /**
     * @description 当前分类当前用户博文数量
     * @author lilei
     * @Time 2021/4/18
     * @updateTime 2021/4/18
     */
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

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
        this.sortName = sortName;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

    public Long getParentSortId() {
        return parentSortId;
    }

    public void setParentSortId(Long parentSortId) {
        this.parentSortId = parentSortId;
    }
}
