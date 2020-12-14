package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.UserMapper;
import pers.lilei.blog.po.User;
import pers.lilei.blog.service.UserService;

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

    @Override
    public Integer addUserSelective(User User) {
        return userMapper.insertSelective(User);
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
