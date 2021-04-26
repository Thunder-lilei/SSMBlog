package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.bean.UserFriend;
import pers.lilei.blog.bean.UserFriendExample;
import pers.lilei.blog.param.UserFunParam;
import pers.lilei.blog.param.UserParam;

public interface UserFriendMapper {
    long countByExample(UserFriendExample example);

    int deleteByExample(UserFriendExample example);

    int deleteByPrimaryKey(Long id);

    int deleteByUserIdWithUserFriendId(@Param("userId") Long userId, @Param("userFriendId") Long userFriendId);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    List<UserFriend> selectByExample(UserFriendExample example);

    UserFriend selectByPrimaryKey(Long id);

    String getNickNameByFriendId(Long userFriendId);

    List<Long> getAllFriendIdByUserId(Long userId);

    List<Long> getAllFriendIdByUserIdAndKey(@Param("userId") Long userId, @Param("key") String key);

    UserFriend getByUserIdAndUserFriendId(@Param("userId") Long userId, @Param("userFriendId") Long userFriendId);

    int updateByExampleSelective(@Param("record") UserFriend record, @Param("example") UserFriendExample example);

    int updateByExample(@Param("record") UserFriend record, @Param("example") UserFriendExample example);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    int updateFriendNickNameByUserIdAndUserFriendId(UserFriend userFriend);

    int selectUserFunCount(@Param("param") UserFunParam userFunParam);
}
