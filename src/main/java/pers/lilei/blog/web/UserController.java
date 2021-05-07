package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lilei.blog.bean.resultBean.UserResultBean;
import pers.lilei.blog.constant.RoleConstant;
import pers.lilei.blog.bean.User;
import pers.lilei.blog.param.UserLoginParam;
import pers.lilei.blog.param.UserParam;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;
import pers.lilei.blog.util.MailUtils;
import pers.lilei.blog.util.NumberUtil;
import pers.lilei.blog.util.ResultMessage;

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
        if (user != null) {
            User LoginUser = userService.selectByUserName(user.getUserName());
            if (LoginUser != null) {
                if (BCrypt.checkpw(user.getUserPassword(),LoginUser.getUserPassword())) {
                    session.setAttribute("user", LoginUser);
                    return ResultMessage.successMessage();
                }else {
                    return ResultMessage.waringMessage("密码错误！");
                }
            } else {
                return ResultMessage.waringMessage("账号错误！");
            }
        } else {
            return ResultMessage.waringMessage("请填写用户信息！");
        }
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
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResultMessage.successMessageResult("loginUser", user);
        } else {
            return ResultMessage.waringMessage("未登录！");
        }
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
        User user = (User) session.getAttribute("updateUser");
        if (user != null) {
            session.setAttribute("updateUser", null);
            return ResultMessage.successMessageResult("updateUser", user);
        } else {
            return ResultMessage.waringMessage("没有设置需要修改信息的用户！");
        }
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
        User user = userService.selectByPrimaryKey(updateUserId);
        if (user != null) {
            session.setAttribute("updateUser", user);
            return ResultMessage.successMessage();
        } else {
            return ResultMessage.waringMessage("未查询到用户信息！");
        }
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
        User user = userService.selectByPrimaryKey(userId);
        if (user != null) {
            return ResultMessage.successMessageResult("updateUser", user);
        } else {
            return ResultMessage.waringMessage("未查询到用户！");
        }
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
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.invalidate();
            return ResultMessage.successMessage();
        } else {
            return ResultMessage.waringMessage("未登录！");
        }
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
        if (!userService.addUserSelective(user).equals(0)) {
            return ResultMessage.successMessage();
        } else {
            return ResultMessage.waringMessage("尝试更换用户名！");
        }
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
        User loginUser = (User) session.getAttribute("user");
        if (user != null) {
            if (!userService.updateUserSelective(user).equals(0)) {
                //当前登录用户信息更新
                if (loginUser != null && loginUser.getUserId().equals(user.getUserId())) {
                    session.setAttribute("user", user);
                }
                return ResultMessage.successMessage();
            } else {
                return ResultMessage.waringMessage("尝试更换用户名！电话已被注册！邮箱已被注册！");
            }
        } else {
            return ResultMessage.waringMessage("请填写用户基本信息！");
        }
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
        //验证码校验
        Integer sessionCode = (Integer) session.getAttribute("code");
        if (!sessionCode.equals(code)) {
            return ResultMessage.waringMessage("请填写正确的验证码！");
        }
        if (user.getUserPassword() == null) {
            return ResultMessage.waringMessage("请填写密码！");
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
                return ResultMessage.successMessage();
            }else {
                return ResultMessage.waringMessage("密码修改失败！");
            }
        }else {
            return ResultMessage.waringMessage("电话或邮箱未匹配！");
        }
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
        return ResultMessage.successMessageResult("userPageInfo", userService.selectUserBaseInfoByKey(pageNow, pageSize, key));
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
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResultMessage.successMessageResult("userPageInfo",
                    userService.selectUserBaseInfoByKeyWithoutMine(pageNow, pageSize, key, user.getUserId()));
        } else {
            return ResultMessage.waringMessage("未登录！");
        }
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
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResultMessage.successMessageResult("userList",
                    userService.selectUserBaseInfoByKeyWithoutMineList(key, user.getUserId()));
        } else {
            return ResultMessage.waringMessage("未登录！");
        }
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
        User user = (User) session.getAttribute("user");
        if (user != null && RoleConstant.adminNum.equals(user.getUserRole())) {
            return ResultMessage.successMessageResult("allUserPageInfo",
                    userService.selectAllUserBaseInfo(pageNow, pageSize));
        } else {
            return ResultMessage.waringMessage("非管理员账号！");
        }
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
        if (!userService.deleteUserByUserId(userId).equals(0)) {
            return ResultMessage.successMessage();
        }else {
            return ResultMessage.waringMessage("删除失败");
        }
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
        User user = (User) session.getAttribute("user");
        user.setUserProfilePhoto(imgUrl);
        if (!userService.updateUserSelective(user).equals(0)) {
            return ResultMessage.successMessage();
        }else {
            return ResultMessage.waringMessage("更改失败！");
        }
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
        User user;
        //选择判断方式
        if (userName != null) {
            user = userService.selectByUserName(userName);
        } else if (email != null) {
            user = userService.selectByEmail(email);
        } else if (tel != null) {
            user = userService.selectByTel(tel);
        } else {
            return ResultMessage.waringMessage("请选择判断条件！");
        }
        //抛出自己
        if (user != null && !user.getUserId().equals(userId)) {
            return ResultMessage.successMessageBoolean(true);
        } else {
            return ResultMessage.successMessageBoolean(false);
        }
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
        User user = userService.selectByPrimaryKey(userId);
        if (user != null) {
            return ResultMessage.successMessageResult("showUser", user);
        } else {
            return ResultMessage.waringMessage("获取失败！");
        }
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
        User user = (User) session.getAttribute("showUser");
        if (user != null) {
            session.removeAttribute("showUser");
            return ResultMessage.successMessageResult("user", user);
        } else {
            return ResultMessage.waringMessage("获取失败！");
        }
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
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResultMessage.successMessageResult("result", RoleConstant.adminNum.equals(user.getUserRole()));
        } else {
            return ResultMessage.waringMessage("未登录！");
        }
    }

    /**
     * @description 添加管理
     * @author lilei
     * @Time 2021/5/7
     * @updateTime 2021/5/7
     */
    @ResponseBody
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    private Map<String,Object> addAdmin(@RequestBody UserParam userParam){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getUserId().equals(userParam.getUserId())) {
                return ResultMessage.waringMessage("您已经是管理员了！");
            }
            if (userService.addAdmin(userParam) > 0) {
                return ResultMessage.successMessage();
            } else {
                return ResultMessage.waringMessage("设置管理员失败！");
            }
        } else {
            return ResultMessage.waringMessage("未登录！");
        }
    }
    /**
     * @description 设置用户
     * @author lilei
     * @Time 2021/5/7
     * @updateTime 2021/5/7
     */
    @ResponseBody
    @RequestMapping(value = "/removeAdmin", method = RequestMethod.POST)
    private Map<String,Object> removeAdmin(@RequestBody UserParam userParam){
        User user = (User) session.getAttribute("user");
        if (user.getUserId().equals(userParam.getUserId())) {
            return ResultMessage.waringMessage("您不能移除自己的管理员身份！");
        }
        if (userService.removeAdmin(userParam) > 0) {
            return ResultMessage.successMessage();
        } else {
            return ResultMessage.waringMessage("设置用户失败！");
        }
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
        if (userService.selectByEmail(mail) == null) {
            return ResultMessage.waringMessage("该邮箱未注册！");
        }
        //生成随机验证码
//        Integer code = (int)((Math.random()*9+1)*100000);
        String code = NumberUtil.getCode();
        MailUtils.SendMail(mail, code, "SSM个人博客系统给您发送的登录验证码为：");
        //设置预登陆的邮箱和验证码
        session.setAttribute("mailCode", code);
        session.setAttribute("loginMail", mail);
        return ResultMessage.successMessage();
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
        //非密码登录用户
        if (userLoginParam.getMail().equals("") || userLoginParam.getMail() == null) {
            //验证登录邮箱，避免更换了登录邮箱
            String loginEmail = (String) session.getAttribute("loginMail");
            if (!loginEmail.equals("") && !userLoginParam.getMail().equals(loginEmail)) {
                return ResultMessage.waringMessage("邮箱不匹配！");
            }
        }
        //获取邮箱用户
        User user = userService.selectByEmail(userLoginParam.getMail());
        //使用密码登录
        if (!userLoginParam.getPassword().equals("") && userLoginParam.getPassword() != null) {
            //密码校验
            if (user != null && BCrypt.checkpw(userLoginParam.getPassword(), user.getUserPassword())) {
                session.setAttribute("user", user);
                return ResultMessage.successMessage();
            } else {
                return ResultMessage.waringMessage("密码错误！");
            }
        } else {
            //验证码校验
            if (!userLoginParam.getCode().equals(session.getAttribute("mailCode"))) {
                return ResultMessage.waringMessage("验证码错误！");
            } else {
                session.removeAttribute("mailCode");
                session.setAttribute("user", user);
                return ResultMessage.successMessage();
            }
        }
    }
    /**
     * @description 获取用户信息
     * @author lilei
     * @Time 2021/4/24
     * @updateTime 2021/4/24
     */
    @ResponseBody
    @RequestMapping(value = "/getArticleUser", method = RequestMethod.POST)
    private Map<String,Object> getArticleUser(@RequestBody UserParam userParam){
        UserResultBean userResultBean = userService.getUserById(userParam);
        if (userResultBean != null) {
            return ResultMessage.successMessageResult("articleUser", userResultBean);
        } else {
            return ResultMessage.waringMessage("未查询到该用户信息！");
        }
    }
}
