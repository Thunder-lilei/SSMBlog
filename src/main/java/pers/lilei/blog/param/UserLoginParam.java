package pers.lilei.blog.param;

/**
 * <h3>SSMBlog</h3>
 * <p>用户登录参数</p>
 *
 * @author : 李雷
 * @date : 2021-04-21 13:23
 **/
public class UserLoginParam {
    /**
     * @description 邮箱
     * @author lilei
     * @Time 2021/4/21
     * @updateTime 2021/4/21
     */
    private String mail;
    /**
     * @description 验证码
     * @author lilei
     * @Time 2021/4/21
     * @updateTime 2021/4/21
     */
    private int code;
    /**
     * @description 密码
     * @author lilei
     * @Time 2021/4/21
     * @updateTime 2021/4/21
     */
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
