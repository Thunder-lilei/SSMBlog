package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>分页包含用户信息参数</p>
 *
 * @author : 李雷
 * @date : 2021-04-18 15:42
 **/
public class PageUserParam {
    /**
     * @description 分页参数
     * @author lilei
     * @Time 2021/4/18
     * @updateTime 2021/4/18
     */
    private PageParam pageParam;
    /**
     * @description 用户参数
     * @author lilei
     * @Time 2021/4/18
     * @updateTime 2021/4/18
     */
    private UserParam userParam;

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    public UserParam getUserParam() {
        return userParam;
    }

    public void setUserParam(UserParam userParam) {
        this.userParam = userParam;
    }
}
