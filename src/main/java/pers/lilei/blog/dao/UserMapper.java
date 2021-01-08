package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.po.User;
import pers.lilei.blog.po.UserExample;
import pers.lilei.blog.pojo.UserBaseInfoPojo;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    UserBaseInfoPojo selectUserBaseInfoByPrimaryKey(Long userId);

    String selectUserPasswordByPrimaryKey(Long userId);

    User selectByUserName(String userName);

    User selectByEmail(String email);

    User selectByTel(Long tel);

    User selectByUserNameAndEmailAndTel(User user);

    User selectByUserNameAndEmailAndTelWithoutUserId(User user);

    List<UserBaseInfoPojo> selectUserBaseInfoByKey(String key);

    List<UserBaseInfoPojo> selectUserBaseInfoByKeyWithoutMine(@Param("key") String key, @Param("userId") Long userId);

    List<UserBaseInfoPojo> selectAllUserBaseInfo();

    List<UserBaseInfoPojo> getAllByUserId(@Param("userIdList") List<Long> userIdList);

    List<UserBaseInfoPojo> getAllByUserIdAndKey(@Param("userIdList") List<Long> userIdList, @Param("key") String key);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updatePasswordByTel(User record);

    int updatePasswordByEmail(User record);

    int updateByPrimaryKey(User record);
}
