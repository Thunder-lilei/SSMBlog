package pers.lilei.blog.service;

import pers.lilei.blog.po.User;

/**
 * <h3>SSMBlog</h3>
 * <p>用户业务逻辑接口</p>
 *
 * @author : 李雷
 * @date : 2020-12-11 11:22
 **/
public interface UserService {
    User selectByUserName(String userName);

    User selectByEmail(String email);

    User selectByTel(Long tel);

    Integer addUserSelective(User User);

    Integer deleteUserByUserId(Long userId);

    Integer updateUserSelective(User User);
}
