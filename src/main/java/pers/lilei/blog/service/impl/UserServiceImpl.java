package pers.lilei.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.bean.resultBean.UserResultBean;
import pers.lilei.blog.constant.RoleConstant;
import pers.lilei.blog.dao.UserFriendMapper;
import pers.lilei.blog.dao.UserMapper;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.param.UserBaseInfoParam;
import pers.lilei.blog.param.UserParam;
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
    private final UserFriendMapper userFriendMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserFriendMapper userFriendMapper) {
        this.userMapper = userMapper;
        this.userFriendMapper = userFriendMapper;
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
    public PageInfo<UserBaseInfoParam> selectUserBaseInfoByKey(int pageNow, int pageSize, String key) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.selectUserBaseInfoByKey(key);
        return new PageInfo<>(userBaseInfoParamList);
    }

    @Override
    public PageInfo<UserBaseInfoParam> selectUserBaseInfoByKeyWithoutMine(int pageNow, int pageSize, String key, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.selectUserBaseInfoByKeyWithoutMine(key, userId);
        return new PageInfo<>(userBaseInfoParamList);
    }

    @Override
    public List<UserBaseInfoParam> selectUserBaseInfoByKeyWithoutMineList(String key, Long userId) {
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.selectUserBaseInfoByKeyWithoutMine(key, userId);
        return userBaseInfoParamList;
    }

    @Override
    public PageInfo<UserBaseInfoParam> selectAllUserBaseInfo(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.selectAllUserBaseInfo();
        return new PageInfo<>(userBaseInfoParamList);
    }

    @Override
    public PageInfo<UserBaseInfoParam> getAllByUserId(int pageNow, int pageSize, List<Long> userIdList) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.getAllByUserId(userIdList);
        return new PageInfo<>(userBaseInfoParamList);
    }

    /*
     * @Author 李雷
     * @Description
     * 获取好友列表 含备注的设置备注
     * @CreateDate 15:16 2021/2/13
     * @UpdateDate 15:16 2021/2/13
     * @Param [pageNow, pageSize, userIdList]
     * @return com.github.pagehelper.PageInfo<pers.lilei.blog.pojo.UserBaseInfoPojo>
     **/
    @Override
    public PageInfo<UserBaseInfoParam> getFriendByUserId(int pageNow, int pageSize, List<Long> userIdList, Long userId) {
        PageHelper.startPage(pageNow, pageSize);
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.getAllByUserId(userIdList);
        for (UserBaseInfoParam userBaseInfoParam : userBaseInfoParamList) {
            //设置备注
            String friendNickName = userFriendMapper.getNickNameByFriendId(userBaseInfoParam.getUserId(), userId);
            if (friendNickName != null && !friendNickName.equals("") && !friendNickName.isEmpty()) {
                userBaseInfoParam.setUserNickname(friendNickName);
            }
        }
        return new PageInfo<>(userBaseInfoParamList);
    }

    @Override
    public List<UserBaseInfoParam> getAllByUserIdList(List<Long> userIdList) {
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.getAllByUserId(userIdList);
        return userBaseInfoParamList;
    }

    /*
     * @Author 李雷
     * @Description
     * 获取好友列表 设置备注
     * @CreateDate 15:33 2021/2/13
     * @UpdateDate 15:33 2021/2/13
     * @Param [userIdList]
     * @return java.util.List<pers.lilei.blog.pojo.UserBaseInfoPojo>
     **/
    @Override
    public List<UserBaseInfoParam> getFriendByUserIdList(List<Long> userIdList, Long userId) {
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.getAllByUserId(userIdList);
        for (UserBaseInfoParam userBaseInfoParam : userBaseInfoParamList) {
            //设置备注
            String friendNickName = userFriendMapper.getNickNameByFriendId(userBaseInfoParam.getUserId(), userId);
            if (friendNickName != null && !friendNickName.equals("") && !friendNickName.isEmpty()) {
                userBaseInfoParam.setUserNickname(friendNickName);
            }
        }
        return userBaseInfoParamList;
    }

    @Override
    public List<UserBaseInfoParam> getAllByUserIdAndKeyList(List<Long> userIdList, String key) {
        List<UserBaseInfoParam> userBaseInfoParamList = userMapper.getAllByUserIdAndKey(userIdList, key);
        return userBaseInfoParamList;
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

    @Override
    public UserResultBean getUserById(UserParam userParam) {
        return userMapper.selectUserById(userParam);
    }

    @Override
    public int addAdmin(UserParam userParam) {
        User user = new User();
        user.setUserId(userParam.getUserId());
        user.setUserRole(RoleConstant.adminNum);
        //修改该用户角色为管理员
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int removeAdmin(UserParam userParam) {
        User user = new User();
        user.setUserId(userParam.getUserId());
        user.setUserRole(RoleConstant.userNum);
        //修改该用户角色为用户
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
