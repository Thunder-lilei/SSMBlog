package pers.lilei.blog.web;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.User;
import pers.lilei.blog.po.UserFriend;
import pers.lilei.blog.pojo.UserBaseInfoPojo;
import pers.lilei.blog.service.UserFriendService;
import pers.lilei.blog.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>用户好友Controller层</p>
 *
 * @author : 李雷
 * @date : 2021-01-06 12:34
 **/
@Controller
@RequestMapping(value = "/userFriend")
public class UserFriendController extends BaseController{
    UserFriendService userFriendService;
    UserService userService;

    @Autowired
    public UserFriendController(UserFriendService userFriendService, UserService userService) {
        this.userFriendService = userFriendService;
        this.userService = userService;
    }

    /*
     * @Author 李雷
     * @Description
     * 分页查询登录用户的所有好友的基本信息
     * @CreateDate 13:06 2021/1/6
     * @UpdateDate 13:06 2021/1/6
     * @Param [pageNow, pageSize]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getMyFriend", method = RequestMethod.POST)
    private Map<String,Object> getMyFriend(@RequestParam int pageNow, @RequestParam int pageSize) {
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        PageInfo<UserBaseInfoPojo> userBaseInfoPojoPageInfo = new PageInfo<>();
        if (user != null) {
            //获取用户的所有好友的id
            List<Long> friendIdList = userFriendService.getAllFriendIdByUserId(user.getUserId());
            if (!friendIdList.isEmpty()) {
                //获取好友信息
                userBaseInfoPojoPageInfo = userService.getFriendByUserId(pageNow, pageSize, friendIdList);
            }
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("userPageInfo", userBaseInfoPojoPageInfo);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 查询登录用户的所有好友的基本信息
     * @CreateDate 14:11 2021/1/7
     * @UpdateDate 14:11 2021/1/7
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getMyFriendList", method = RequestMethod.POST)
    private Map<String,Object> getMyFriendList() {
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        List<UserBaseInfoPojo> userBaseInfoPojoList = new ArrayList<>();
        if (user != null) {
            //获取用户的所有好友的id
            List<Long> friendIdList = userFriendService.getAllFriendIdByUserId(user.getUserId());
            if (!friendIdList.isEmpty()) {
                //获取好友信息
                userBaseInfoPojoList = userService.getFriendByUserIdList(friendIdList);
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            }
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("userList", userBaseInfoPojoList);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 根据关键词分页查询登录用户的所有好友的基本信息
     * @CreateDate 18:53 2021/1/6
     * @UpdateDate 18:53 2021/1/6
     * @Param [pageNow, pageSize, key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getMyFriendByKey", method = RequestMethod.POST)
    private Map<String,Object> getMyFriendByKey(@RequestParam int pageNow, @RequestParam int pageSize, @RequestParam String key) {
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        PageInfo<UserBaseInfoPojo> userBaseInfoPojoPageInfo = new PageInfo<>();
        if (user != null) {
            //通过关键词获取用户的所有好友的id
            List<Long> friendIdList = userFriendService.getAllFriendIdByUserIdAndKey(user.getUserId(), key);
            if (!friendIdList.isEmpty()) {
                //获取好友信息
                userBaseInfoPojoPageInfo = userService.getAllByUserId(pageNow, pageSize, friendIdList);
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            }
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("userPageInfo", userBaseInfoPojoPageInfo);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 根据关键词查询登录用户的所有好友的基本信息
     * @CreateDate 14:13 2021/1/7
     * @UpdateDate 2021-1-8 12:38:12
     * @Param [key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getMyFriendByKeyList", method = RequestMethod.POST)
    private Map<String,Object> getMyFriendByKeyList(@RequestParam String key) {
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        List<UserBaseInfoPojo> userBaseInfoPojoList = new ArrayList<>();
        if (user != null) {
            //通过关键词获取用户的所有好友的id
            List<Long> friendIdList = userFriendService.getAllFriendIdByUserId(user.getUserId());
            if (!friendIdList.isEmpty()) {
                //获取好友信息
                userBaseInfoPojoList = userService.getAllByUserIdAndKeyList(friendIdList, key);
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            }
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("userList", userBaseInfoPojoList);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 登录用户添加好友
     * 不允许重复添加5
     * @CreateDate 13:24 2021/1/6
     * @UpdateDate 13:24 2021/1/6
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    private Map<String,Object> addFriend(@RequestParam Long userId) {
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(user.getUserId());
            userFriend.setUserFriendId(userId);
            //结果判断
            Integer result = userFriendService.addFriend(userFriend);
            if (result.equals(1)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else if (result.equals(-1)) {
                modelMap.put(MessageConstant.MESSAGE, "该用户已经是你的好友！");
            } else {
                modelMap.put(MessageConstant.MESSAGE, "添加失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 移除好友
     * @CreateDate 13:44 2021/1/6
     * @UpdateDate 13:44 2021/1/6
     * @Param [userFriendId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
    private Map<String,Object> deleteFriend(@RequestParam Long userFriendId) {
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (!userFriendService.deleteFriend(user.getUserId(), userFriendId).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "移除失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 修改好友备注
     * @CreateDate 14:04 2021/1/6
     * @UpdateDate 14:04 2021/1/6
     * @Param [userFriendId, nickName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updateFriendNickName", method = RequestMethod.POST)
    private Map<String,Object> updateFriendNickName(@RequestParam Long userFriendId, @RequestParam String nickName) {
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(user.getUserId());
            userFriend.setUserFriendId(userFriendId);
            userFriend.setUserNickname(nickName);
            if (!userFriendService.updateFriendNickNameByUserIdAndUserFriendId(userFriend).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "修改失败！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
}
