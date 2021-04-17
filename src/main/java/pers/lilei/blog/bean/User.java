package pers.lilei.blog.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private Long userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userProfilePhoto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date userBirthday;

    private Byte userAge;

    private Long userTelephoneNumber;

    private String userNickname;

    private Date createTime;

    private Date updateTime;

    public User() {}

    public User(String userName, String userPassword, String userEmail, String userProfilePhoto, Date userBirthday,
                Byte userAge, Long userTelephoneNumber, String userNickname) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userProfilePhoto = userProfilePhoto;
        this.userBirthday = userBirthday;
        this.userAge = userAge;
        this.userTelephoneNumber = userTelephoneNumber;
        this.userNickname = userNickname;
    }

    public void setTimeNull() {
        this.createTime = null;
        this.updateTime = null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(String userProfilePhoto) {
        this.userProfilePhoto = userProfilePhoto == null ? null : userProfilePhoto.trim();
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Byte getUserAge() {
        return userAge;
    }

    public void setUserAge(Byte userAge) {
        this.userAge = userAge;
    }

    public Long getUserTelephoneNumber() {
        return userTelephoneNumber;
    }

    public void setUserTelephoneNumber(Long userTelephoneNumber) {
        this.userTelephoneNumber = userTelephoneNumber;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
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
}
