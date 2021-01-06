package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.UserFriend;
import pers.lilei.blog.po.UserFriendExample;

public interface UserFriendMapper {
    long countByExample(UserFriendExample example);

    int deleteByExample(UserFriendExample example);

    int deleteByPrimaryKey(Long id);

    int deleteByUserIdWithUserFriendId(@Param("userId") Long userId, @Param("userFriendId") Long userFriendId);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    List<UserFriend> selectByExample(UserFriendExample example);

    UserFriend selectByPrimaryKey(Long id);

    List<Long> getAllFriendIdByUserId(Long userId);

    List<Long> getAllFriendIdByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    int updateByExampleSelective(@Param("record") UserFriend record, @Param("example") UserFriendExample example);

    int updateByExample(@Param("record") UserFriend record, @Param("example") UserFriendExample example);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    int updateFriendNickNameByUserIdAndUserFriendId(UserFriend userFriend);
}
