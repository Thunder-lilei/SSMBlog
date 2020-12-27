package pers.lilei.blog.service;

import com.github.pagehelper.PageInfo;
import pers.lilei.blog.po.User;
import pers.lilei.blog.pojo.UserBaseInfoPojo;

import java.util.List;

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

    PageInfo<UserBaseInfoPojo> selectUserBaseInfoByKey(int pageNow, int pageSize, String key);

    PageInfo<UserBaseInfoPojo> selectAllUserBaseInfo(int pageNow, int pageSize);

    Integer addUserSelective(User user);

    Integer deleteUserByUserId(Long userId);

    Integer updateUserSelective(User user);

    Integer updatePasswordByTel(User record);

    Integer updatePasswordByEmail(User record);
}
