package pers.lilei.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lilei.blog.dao.UserFriendMapper;
import pers.lilei.blog.po.UserFriend;
import pers.lilei.blog.service.UserFriendService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>用户好友Service层实现类</p>
 *
 * @author : 李雷
 * @date : 2021-01-06 12:35
 **/
@Service
public class UserFriendServiceImpl implements UserFriendService {
    UserFriendMapper userFriendMapper;
    @Autowired
    public UserFriendServiceImpl(UserFriendMapper userFriendMapper) {
        this.userFriendMapper = userFriendMapper;
    }

    @Override
    public List<Long> getAllFriendIdByUserId(Long userId) {
        return userFriendMapper.getAllFriendIdByUserId(userId);
    }

    @Override
    public List<Long> getAllFriendIdByUserIdAndKey(Long userId, String key) {
        return userFriendMapper.getAllFriendIdByUserIdAndKey(userId, key);
    }

    @Override
    public UserFriend getByUserIdAndUserFriendId(Long userId, Long userFriendId) {
        return userFriendMapper.getByUserIdAndUserFriendId(userId, userFriendId);
    }

    /*
     * @Author 李雷
     * @Description
     * 添加好友 不允许重复添加
     * 重复添加返回-1
     * @CreateDate 13:48 2021/1/7
     * @UpdateDate 13:48 2021/1/7
     * @Param [userFriend]
     * @return java.lang.Integer
     **/
    @Override
    public Integer addFriend(UserFriend userFriend) {
        if (getByUserIdAndUserFriendId(userFriend.getUserId(), userFriend.getUserFriendId()) != null) {
            return -1;
        }
        return userFriendMapper.insertSelective(userFriend);
    }

    @Override
    public Integer deleteFriend(Long userId, Long userFriendId) {
        return userFriendMapper.deleteByUserIdWithUserFriendId(userId, userFriendId);
    }

    @Override
    public Integer updateFriendNickNameByUserIdAndUserFriendId(UserFriend userFriend) {
        return userFriendMapper.updateFriendNickNameByUserIdAndUserFriendId(userFriend);
    }
}
