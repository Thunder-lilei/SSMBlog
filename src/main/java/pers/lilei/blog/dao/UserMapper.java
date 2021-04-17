package pers.lilei.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.bean.UserExample;
import pers.lilei.blog.param.UserBaseInfoParam;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    UserBaseInfoParam selectUserBaseInfoByPrimaryKey(Long userId);

    String selectUserPasswordByPrimaryKey(Long userId);

    User selectByUserName(String userName);

    User selectByEmail(String email);

    User selectByTel(Long tel);

    User selectByUserNameAndEmailAndTel(User user);

    User selectByUserNameAndEmailAndTelWithoutUserId(User user);

    List<UserBaseInfoParam> selectUserBaseInfoByKey(String key);

    List<UserBaseInfoParam> selectUserBaseInfoByKeyWithoutMine(@Param("key") String key, @Param("userId") Long userId);

    List<UserBaseInfoParam> selectAllUserBaseInfo();

    List<UserBaseInfoParam> getAllByUserId(@Param("userIdList") List<Long> userIdList);

    List<UserBaseInfoParam> getAllByUserIdAndKey(@Param("userIdList") List<Long> userIdList, @Param("key") String key);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updatePasswordByTel(User record);

    int updatePasswordByEmail(User record);

    int updateByPrimaryKey(User record);
}
