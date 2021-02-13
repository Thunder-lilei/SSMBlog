package pers.lilei.blog.service;

import com.github.pagehelper.PageInfo;
import pers.lilei.blog.po.User;
import pers.lilei.blog.pojo.UserBaseInfoPojo;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>用户业务逻辑接口</p>
 *
 * @author : 李雷
 * @date : 2020-12-11 11:22
 **/
public interface UserService {
    User selectByUserName(String userName);

    User selectByEmail(String email);

    User selectByTel(Long tel);

    User selectByPrimaryKey(Long userId);

    String selectUserPasswordByPrimaryKey(Long userId);

    User selectByUserNameAndEmailAndTel(User user);

    User selectByUserNameAndEmailAndTelWithoutUserId(User user);

    PageInfo<UserBaseInfoPojo> selectUserBaseInfoByKey(int pageNow, int pageSize, String key);

    PageInfo<UserBaseInfoPojo> selectUserBaseInfoByKeyWithoutMine(int pageNow, int pageSize, String key, Long userId);

    List<UserBaseInfoPojo> selectUserBaseInfoByKeyWithoutMineList(String key, Long userId);

    PageInfo<UserBaseInfoPojo> selectAllUserBaseInfo(int pageNow, int pageSize);

    PageInfo<UserBaseInfoPojo> getAllByUserId(int pageNow, int pageSize, List<Long> userIdList);

    PageInfo<UserBaseInfoPojo> getFriendByUserId(int pageNow, int pageSize, List<Long> userIdList);

    List<UserBaseInfoPojo> getAllByUserIdList(List<Long> userIdList);

    List<UserBaseInfoPojo> getFriendByUserIdList(List<Long> userIdList);

    List<UserBaseInfoPojo> getAllByUserIdAndKeyList(List<Long> userIdList, String key);

    Integer addUserSelective(User user);

    Integer deleteUserByUserId(Long userId);

    Integer updateUserSelective(User user);

    Integer updatePasswordByTel(User record);

    Integer updatePasswordByEmail(User record);
}
