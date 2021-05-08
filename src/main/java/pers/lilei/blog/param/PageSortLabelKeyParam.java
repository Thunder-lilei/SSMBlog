package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>分类、标签关键词分页参数</p>
 *
 * @author : 李雷
 * @date : 2021-05-09 00:11
 **/
public class PageSortLabelKeyParam {
    /**
     * @description 分页参数
     * @author lilei
     * @Time 2021/5/8
     * @updateTime 2021/5/8
     */
    private PageParam pageParam;
    /**
     * @description 关键词
     * @author lilei
     * @Time 2021/5/8
     * @updateTime 2021/5/8
     */
    private String key;
    /**
     * @description 分类ID
     * @author lilei
     * @Time 2021/5/9
     * @updateTime 2021/5/9
     */
    private Long sortId;
    /**
     * @description 标签ID
     * @author lilei
     * @Time 2021/5/9
     * @updateTime 2021/5/9
     */
    private Long labelId;

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }
}
