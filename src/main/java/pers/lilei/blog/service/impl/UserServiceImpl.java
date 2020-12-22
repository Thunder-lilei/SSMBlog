package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.UserMapper;
import pers.lilei.blog.po.User;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;

/**
 * <h3>SSMBlog</h3>
 * <p>用户业务逻辑实现类</p>
 *
 * @author : 李雷
 * @date : 2020-12-11 11:20
 **/
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public User selectByTel(Long tel) {
        return userMapper.selectByTel(tel);
    }

    /*
     * @Author 李雷
     * @Description
     * 添加用户
     * 用户名、电话、邮箱不能重复
     * 密码加密
     * @CreateDate 12:20 2020/12/22
     * @UpdateDate 12:20 2020/12/22
     * @Param [user]
     * @return java.lang.Integer
     **/
    @Override
    public Integer addUserSelective(User user) {
        if (selectByUserName(user.getUserName()) != null || selectByEmail(user.getUserEmail()) != null
                || selectByTel(user.getUserTelephoneNumber()) != null) {return 0;}
        user.setUserPassword(BCrypt.hashpw(user.getUserPassword(),BCrypt.gensalt()));
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer deleteUserByUserId(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public Integer updateUserSelective(User User) {
        return userMapper.updateByPrimaryKeySelective(User);
    }
}
