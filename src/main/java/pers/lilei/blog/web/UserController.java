package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.constant.RoleConstant;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.param.UserLoginParam;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;
import pers.lilei.blog.util.MailUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>用户Control层</p>
 *
 * @author : 李雷
 * @date : 2020-12-22 12:25
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*
     * @Author 李雷
     * @Description
     * 用户名登录
     * @CreateDate 12:50 2020/12/22
     * @UpdateDate 12:50 2020/12/22
     * @Param [user]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/toLoginByUserName", method = RequestMethod.POST)
    private Map<String,Object> toLoginByUserName(@RequestBody User user){
        Map<String,Object> modelMap = new HashMap<>();
        if (user != null) {
            User LoginUser = userService.selectByUserName(user.getUserName());
            if (LoginUser != null && BCrypt.checkpw(user.getUserPassword(),LoginUser.getUserPassword())) {
                session.setAttribute("user", LoginUser);
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "密码错误！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "请填写用户信息！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取登录用户信息
     * @CreateDate 16:23 2020/12/22
     * @UpdateDate 16:23 2020/12/22
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getLoginUser", method = RequestMethod.POST)
    private Map<String,Object> getLoginUser(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put("loginUser",user);
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取需要更新的用户信息
     * 移除位于session的用户信息
     * @CreateDate 14:50 2020/12/28
     * @UpdateDate 14:50 2020/12/28
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getUpdateUser", method = RequestMethod.POST)
    private Map<String,Object> getUpdateUser(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("updateUser");
        if (user != null) {
            modelMap.put("updateUser",user);
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            session.setAttribute("updateUser", null);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "没有设置需要修改信息的用户！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 设置需要更新的用户信息
     * @CreateDate 14:51 2020/12/28
     * @UpdateDate 14:51 2020/12/28
     * @Param [updateUserId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/setUpdateUser", method = RequestMethod.POST)
    private Map<String,Object> setUpdateUser(@RequestParam Long updateUserId){
        Map<String,Object> modelMap = new HashMap<>();
        User user = userService.selectByPrimaryKey(updateUserId);
        if (user != null) {
            session.setAttribute("updateUser", user);
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未查询到用户信息！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 通过用户id查询用户
     * @CreateDate 13:37 2020/12/28
     * @UpdateDate 13:37 2020/12/28
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    private Map<String,Object> getUser(@RequestParam Long userId){
        Map<String,Object> modelMap = new HashMap<>();
        User user = userService.selectByPrimaryKey(userId);
        if (user != null) {
            modelMap.put("updateUser",user);
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未查询到用户！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 登出 清空session
     * @CreateDate 16:37 2020/12/22
     * @UpdateDate 16:37 2020/12/22
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private Map<String,Object> logout(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.invalidate();
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 注册用户
     * @CreateDate 20:05 2020/12/22
     * @UpdateDate 20:05 2020/12/22
     * @Param [user]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    private Map<String,Object> registerUser(@RequestBody  User user){
        Map<String,Object> modelMap = new HashMap<>();
        if (!userService.addUserSelective(user).equals(0)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "尝试更换用户名！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 修改用户信息
     * @CreateDate 22:37 2020/12/22
     * @UpdateDate 22:37 2020/12/22
     * @Param [user]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    private Map<String,Object> updateUser(@RequestBody User user){
        Map<String,Object> modelMap = new HashMap<>();
        if (user != null) {
            if (!userService.updateUserSelective(user).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "尝试更换用户名！电话已被注册！邮箱已被注册！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "请填写用户基本信息！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 通过获取验证码修改密码
     * 验证码校验
     * 电话、邮箱获取
     * @CreateDate 14:42 2020/12/27
     * @UpdateDate 14:42 2020/12/27
     * @Param [user, code]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updatePasswordByCode", method = RequestMethod.POST)
    private Map<String,Object> updatePasswordByCode(@RequestBody User user, @RequestParam Integer code){
        Map<String,Object> modelMap = new HashMap<>();
        //验证码校验
        Integer sessionCode = (Integer) session.getAttribute("code");
        if (code == null && !sessionCode.equals(code)) {
            modelMap.put(MessageConstant.MESSAGE, "请填写正确的验证码！");
            return modelMap;
        }
        if (user.getUserPassword() == null) {
            modelMap.put(MessageConstant.MESSAGE, "请填写密码！");
            return modelMap;
        }
        User selectUser;
        if (user.getUserTelephoneNumber() != null) {
            selectUser = userService.selectByTel(user.getUserTelephoneNumber());
        }else if (user.getUserEmail() != null) {
            selectUser = userService.selectByEmail(user.getUserEmail());
        }else {
            selectUser = null;
        }

        if (selectUser != null) {
            selectUser.setUserPassword(user.getUserPassword());
            if (!userService.updateUserSelective(selectUser).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            }else {
                modelMap.put(MessageConstant.MESSAGE, "密码修改失败！");
            }
        }else {
            modelMap.put(MessageConstant.MESSAGE, "电话或邮箱未匹配！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 关键词搜索用户
     * 模糊搜索用户名、昵称
     * @CreateDate 15:59 2020/12/27
     * @UpdateDate 15:59 2020/12/27
     * @Param [key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/selectUserBaseInfoByKey", method = RequestMethod.POST)
    private Map<String,Object> selectUserBaseInfoByKey(@RequestParam Integer pageNow, @RequestParam Integer pageSize, @RequestParam String key) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put("userPageInfo", userService.selectUserBaseInfoByKey(pageNow, pageSize, key));
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 关键词分页查询用户基本信息
     * 不包含登录用户
     * @CreateDate 21:02 2021/1/6
     * @UpdateDate 21:02 2021/1/6
     * @Param [pageNow, pageSize, key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/selectUserBaseInfoByKeyWithoutMine", method = RequestMethod.POST)
    private Map<String,Object> selectUserBaseInfoByKeyWithoutMine(@RequestParam Integer pageNow, @RequestParam Integer pageSize, @RequestParam String key) {
        Map<String, Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("userPageInfo", userService.selectUserBaseInfoByKeyWithoutMine(pageNow, pageSize, key, user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 关键词搜索用户基本信息
     * 不包含登录用户
     * @CreateDate 14:21 2021/1/7
     * @UpdateDate 14:21 2021/1/7
     * @Param [key]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/selectUserBaseInfoByKeyWithoutMineList", method = RequestMethod.POST)
    private Map<String,Object> selectUserBaseInfoByKeyWithoutMineList(@RequestParam String key) {
        Map<String, Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("userList", userService.selectUserBaseInfoByKeyWithoutMineList(key, user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 分页查询所有用户基本信息
     * @CreateDate 9:04 2020/12/28
     * @UpdateDate 9:04 2020/12/28
     * @Param [pageNow, pageSize]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/selectAllUserBaseInfo", method = RequestMethod.POST)
    private Map<String,Object> selectAllUserBaseInfo(@RequestParam Integer pageNow, @RequestParam Integer pageSize) {
        Map<String, Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null && RoleConstant.adminIdList.contains(user.getUserId())) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("allUserPageInfo", userService.selectAllUserBaseInfo(pageNow, pageSize));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "非管理员账号！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 根据用户id删除用户
     * @CreateDate 9:08 2020/12/28
     * @UpdateDate 9:08 2020/12/28
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    private Map<String,Object> deleteUser(@RequestParam Long userId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!userService.deleteUserByUserId(userId).equals(0)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        }else {
            modelMap.put(MessageConstant.MESSAGE, "删除失败");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 更改头像地址
     * @CreateDate 17:58 2020/12/28
     * @UpdateDate 17:58 2020/12/28
     * @Param [imgUrl]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/changeHeadImg", method = RequestMethod.POST)
    private Map<String,Object> changeHeadImg(@RequestParam String imgUrl) {
        Map<String, Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        user.setUserProfilePhoto(imgUrl);
        if (!userService.updateUserSelective(user).equals(0)) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        }else {
            modelMap.put(MessageConstant.MESSAGE, "更改失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 重复信息判断
     * @CreateDate 20:01 2020/12/30
     * @UpdateDate 20:01 2020/12/30
     * @Param [userName, email, tel, id]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/ifHaveSame", method = RequestMethod.POST)
    private Map<String,Object> checkUserName(String userName, String email, Long tel, @RequestParam Long userId) {
        Map<String, Object> modelMap = new HashMap<>();
        User user = null;
        //选择判断方式
        if (userName != null) {
            user = userService.selectByUserName(userName);
        } else if (email != null) {
            user = userService.selectByEmail(email);
        } else if (tel != null) {
            user = userService.selectByTel(tel);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "请选择判断条件！");
        }
        //抛出自己
        if (user != null && !user.getUserId().equals(userId)) {
            modelMap.put(MessageConstant.MESSAGE, true);
        } else {
            modelMap.put(MessageConstant.MESSAGE, false);
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 通过用户id获取用户
     * @CreateDate 14:18 2021/1/8
     * @UpdateDate 14:18 2021/1/8
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/setShowUser", method = RequestMethod.POST)
    private Map<String,Object> setShowUser(@RequestParam Long userId){
        Map<String,Object> modelMap = new HashMap<>();
        User user = userService.selectByPrimaryKey(userId);
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            session.setAttribute("showUser", user);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取用户 返回给前端
     * @CreateDate 14:20 2021/1/8
     * @UpdateDate 14:20 2021/1/8
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getShowUser", method = RequestMethod.POST)
    private Map<String,Object> getShowUser(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("showUser");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("user", user);
            session.setAttribute("showUser", null);
        } else {
            modelMap.put(MessageConstant.MESSAGE, "获取失败！");
        }
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 判断登录用户是否是管理员
     * @CreateDate 20:26 2021/2/8
     * @UpdateDate 20:26 2021/2/8
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/ifAdmin", method = RequestMethod.POST)
    private Map<String,Object> ifAdmin(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            modelMap.put("result", RoleConstant.adminIdList.contains(user.getUserId()));
        } else {
            modelMap.put(MessageConstant.MESSAGE, "未登录！");
        }
        return modelMap;
    }

    /*
     * @Author 李雷
     * @Description
     * 设置邮箱登录验证码
     * 检查是否注册
     * @CreateDate 20:42 2021/2/13
     * @UpdateDate 20:42 2021/2/13
     * @Param [mail]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/setMailCode", method = RequestMethod.POST)
    private Map<String,Object> setMailCode(@RequestParam String mail){
        Map<String,Object> modelMap = new HashMap<>();
        if (userService.selectByEmail(mail) == null) {
            modelMap.put(MessageConstant.MESSAGE, "该邮箱未注册！");
            return modelMap;
        }
        //生成随机验证码
        Integer code = (int)((Math.random()*9+1)*100000);
        MailUtils.SendMail(mail, code.toString(), "SSM个人博客系统给您发送的登录验证码为：");
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        //设置预登陆的邮箱和验证码
        session.setAttribute("mailCode", code);
        session.setAttribute("loginMail", mail);
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 邮箱验证码登录
     * 邮箱匹配验证
     * @CreateDate 20:54 2021/2/13
     * @UpdateDate 20:54 2021/2/13
     * @Param [mail, code]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/mailLogin", method = RequestMethod.POST)
    private Map<String,Object> mailLogin(@RequestBody UserLoginParam userLoginParam){
        Map<String,Object> modelMap = new HashMap<>();
        //非密码登录用户
        if (userLoginParam.getMail().equals("") || userLoginParam.getMail() == null) {
            //验证登录邮箱，避免更换了登录邮箱
            String loginEmail = (String) session.getAttribute("loginMail");
            if (!loginEmail.equals("") && !userLoginParam.getMail().equals(loginEmail)) {
                modelMap.put(MessageConstant.MESSAGE, "邮箱不匹配！");
                return modelMap;
            }
        }
        //获取邮箱用户
        User user = userService.selectByEmail(userLoginParam.getMail());
        //使用密码登录
        if (!userLoginParam.getPassword().equals("") && userLoginParam.getPassword() != null) {
            //密码校验
            if (user != null && BCrypt.checkpw(userLoginParam.getPassword(), user.getUserPassword())) {
                session.setAttribute("user", user);
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "密码错误！");
                return modelMap;
            }
        } else {
            //验证码校验
            if (userLoginParam.getCode() != (int)session.getAttribute("mailCode")) {
                modelMap.put(MessageConstant.MESSAGE, "验证码错误！");
                return modelMap;
            } else {
                session.setAttribute("user", user);
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            }
        }
        return modelMap;
    }
}
