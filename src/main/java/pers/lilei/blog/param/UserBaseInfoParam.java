package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>User基本信息</p>
 *
 * @author : 李雷
 * @date : 2020-12-27 15:45
 **/
public class UserBaseInfoParam {
    private Long userId;

    private String userNickname;

    private String userProfilePhoto;

    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(String userProfilePhoto) {
        this.userProfilePhoto = userProfilePhoto;
    }

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
