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
    public Integer addFriend(UserFriend userFriend) {
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
