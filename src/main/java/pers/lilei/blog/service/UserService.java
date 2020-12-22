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

    User selectByPrimaryKey(Long userId);

    String selectUserPasswordByPrimaryKey(Long userId);

    User selectByUserNameAndEmailAndTel(User user);

    User selectByUserNameAndEmailAndTelWithoutUserId(User user);

    Integer addUserSelective(User user);

    Integer deleteUserByUserId(Long userId);

    Integer updateUserSelective(User user);
}
