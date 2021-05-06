package pers.lilei.blog.service;

import com.github.pagehelper.PageInfo;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.bean.resultBean.UserResultBean;
import pers.lilei.blog.param.UserBaseInfoParam;
import pers.lilei.blog.param.UserParam;

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

    PageInfo<UserBaseInfoParam> selectUserBaseInfoByKey(int pageNow, int pageSize, String key);

    PageInfo<UserBaseInfoParam> selectUserBaseInfoByKeyWithoutMine(int pageNow, int pageSize, String key, Long userId);

    List<UserBaseInfoParam> selectUserBaseInfoByKeyWithoutMineList(String key, Long userId);

    PageInfo<UserBaseInfoParam> selectAllUserBaseInfo(int pageNow, int pageSize);

    PageInfo<UserBaseInfoParam> getAllByUserId(int pageNow, int pageSize, List<Long> userIdList);

    PageInfo<UserBaseInfoParam> getFriendByUserId(int pageNow, int pageSize, List<Long> userIdList, Long userId);

    List<UserBaseInfoParam> getAllByUserIdList(List<Long> userIdList);

    List<UserBaseInfoParam> getFriendByUserIdList(List<Long> userIdList, Long userId);

    List<UserBaseInfoParam> getAllByUserIdAndKeyList(List<Long> userIdList, String key);

    Integer addUserSelective(User user);

    Integer deleteUserByUserId(Long userId);

    Integer updateUserSelective(User user);

    Integer updatePasswordByTel(User record);

    Integer updatePasswordByEmail(User record);

    UserResultBean getUserById(UserParam userParam);

    int addAdmin(UserParam userParam);

    int removeAdmin(UserParam userParam);
}
