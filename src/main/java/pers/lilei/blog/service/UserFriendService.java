package pers.lilei.blog.service;

import pers.lilei.blog.po.UserFriend;
import pers.lilei.blog.pojo.UserBaseInfoPojo;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>用户好友Service层</p>
 *
 * @author : 李雷
 * @date : 2021-01-06 12:35
 **/
public interface UserFriendService {
    List<Long> getAllFriendIdByUserId(Long userId);

    List<Long> getAllFriendIdByUserIdAndKey(Long userId, String key);

    Integer addFriend(UserFriend userFriend);

    Integer deleteFriend(Long userId, Long userFriendId);

    Integer updateFriendNickNameByUserIdAndUserFriendId(UserFriend userFriend);
}
