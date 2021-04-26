package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>用户关注参数</p>
 *
 * @author : 李雷
 * @date : 2021-04-26 13:39
 **/
public class UserFunParam {
    /**
     * @description 用户关注ID
     * @author lilei
     * @Time 2021/4/26
     * @updateTime 2021/4/26
     */
    private Long UserFriendId;
    /**
     * @description 用户ID
     * @author lilei
     * @Time 2021/4/26
     * @updateTime 2021/4/26
     */
    private Long UserId;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getUserFriendId() {
        return UserFriendId;
    }

    public void setUserFriendId(Long userFriendId) {
        UserFriendId = userFriendId;
    }
}
