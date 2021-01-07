package pers.lilei.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.UserMapper;
import pers.lilei.blog.po.User;
import pers.lilei.blog.pojo.UserBaseInfoPojo;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;

import java.util.List;

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
    public User selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public String selectUserPasswordByPrimaryKey(Long userId) {
        return userMapper.selectUserPasswordByPrimaryKey(userId);
    }

    @Override
    public User selectByUserNameAndEmailAndTel(User user) {
        return userMapper.selectByUserNameAndEmailAndTel(user);
    }

    @Override
    public User selectByUserNameAndEmailAndTelWithoutUserId(User user) {
        return userMapper.selectByUserNameAndEmailAndTelWithoutUserId(user);
    }

    @Override
    public PageInfo<UserBaseInfoPojo> selectUserBaseInfoByKey(int pageNow, int pageSize, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoPojo> userBaseInfoPojoList = userMapper.selectUserBaseInfoByKey(key);
        return new PageInfo<>(userBaseInfoPojoList);
    }

    @Override
    public PageInfo<UserBaseInfoPojo> selectUserBaseInfoByKeyWithoutMine(int pageNow, int pageSize, String key, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoPojo> userBaseInfoPojoList = userMapper.selectUserBaseInfoByKeyWithoutMine(key, userId);
        return new PageInfo<>(userBaseInfoPojoList);
    }

    @Override
    public List<UserBaseInfoPojo> selectUserBaseInfoByKeyWithoutMineList(String key, Long userId) {
        List<UserBaseInfoPojo> userBaseInfoPojoList = userMapper.selectUserBaseInfoByKeyWithoutMine(key, userId);
        return userBaseInfoPojoList;
    }

    @Override
    public PageInfo<UserBaseInfoPojo> selectAllUserBaseInfo(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoPojo> userBaseInfoPojoList = userMapper.selectAllUserBaseInfo();
        return new PageInfo<>(userBaseInfoPojoList);
    }

    @Override
    public PageInfo<UserBaseInfoPojo> getAllByUserId(int pageNow, int pageSize, List<Long> userIdList) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoPojo> userBaseInfoPojoList = userMapper.getAllByUserId(userIdList);
        return new PageInfo<>(userBaseInfoPojoList);
    }

    @Override
    public List<UserBaseInfoPojo> getAllByUserIdList(List<Long> userIdList) {
        List<UserBaseInfoPojo> userBaseInfoPojoList = userMapper.getAllByUserId(userIdList);
        return userBaseInfoPojoList;
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
        if (selectByUserNameAndEmailAndTel(user) != null) {return 0;}
        user.setUserPassword(BCrypt.hashpw(user.getUserPassword(),BCrypt.gensalt()));
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer deleteUserByUserId(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    /*
     * @Author 李雷
     * @Description
     * 更新用户信息
     * 新密码加密
     * 用户名 电话 邮箱不能重复
     * @CreateDate 22:25 2020/12/22
     * @UpdateDate 22:25 2020/12/22
     * @Param [user]
     * @return java.lang.Integer
     **/
    @Override
    public Integer updateUserSelective(User user) {
        if (selectByUserNameAndEmailAndTelWithoutUserId(user) != null) {return 0;}
        user.setTimeNull();
        String userPassword = selectUserPasswordByPrimaryKey(user.getUserId());
        if (!user.getUserPassword().equals(userPassword)) {
            user.setUserPassword(BCrypt.hashpw(user.getUserPassword(),BCrypt.gensalt()));
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer updatePasswordByTel(User user) {
        //密码加密
        user.setUserPassword(BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt()));
        return userMapper.updatePasswordByTel(user);
    }

    @Override
    public Integer updatePasswordByEmail(User user) {
        //密码加密
        user.setUserPassword(BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt()));
        return userMapper.updatePasswordByEmail(user);
    }

}
