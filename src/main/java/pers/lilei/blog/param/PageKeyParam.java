package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>关键词分页参数</p>
 *
 * @author : 李雷
 * @date : 2021-05-08 23:48
 **/
public class PageKeyParam {
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
