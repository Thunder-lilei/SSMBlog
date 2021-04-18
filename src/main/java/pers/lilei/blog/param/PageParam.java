package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>分页参数</p>
 *
 * @author : 李雷
 * @date : 2021-04-18 15:32
 **/
public class PageParam {
    /**
     * @description 初始下标
     * @author lilei
     * @Time 2021/4/18
     * @updateTime 2021/4/18
     */
    private int pageIndex;
    /**
     * @description 每页条数
     * @author lilei
     * @Time 2021/4/18
     * @updateTime 2021/4/18
     */
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
