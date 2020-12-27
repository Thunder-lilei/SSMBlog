package pers.lilei.blog.pojo;

/**
 * <h3>SSMBlog</h3>
 * <p>User基本信息</p>
 *
 * @author : 李雷
 * @date : 2020-12-27 15:45
 **/
public class UserBaseInfoPojo {
    private Long userId;

    private String userNickname;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
