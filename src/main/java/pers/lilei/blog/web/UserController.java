package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.User;
import pers.lilei.blog.pojo.UserBaseInfoPojo;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;

import java.util.HashMap;
import java.util.List;
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
    private Map<String,Object> toLoginByUserName(User user){
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
    private Map<String,Object> registerUser(User user){
        Map<String,Object> modelMap = new HashMap<>();
        if (user != null) {
            if (!userService.addUserSelective(user).equals(0)) {
                modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
            } else {
                modelMap.put(MessageConstant.MESSAGE, "尝试更换用户名！");
            }
        } else {
            modelMap.put(MessageConstant.MESSAGE, "请填写用户基本信息！");
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
    private Map<String,Object> updateUser(User user){
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
    private Map<String,Object> updatePasswordByCode(User user, @RequestParam Integer code){
        Map<String,Object> modelMap = new HashMap<>();
        //验证码校验
        if (code == null && !session.getAttribute("code").equals(code)) {
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
    private Map<String,Object> selectUserBaseInfoByKey(@RequestParam String key) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("userList", userService.selectUserBaseInfoByKey(key));
        return modelMap;
    }
    @ResponseBody
    @RequestMapping(value = "/selectAllUserBaseInfo", method = RequestMethod.POST)
    private Map<String,Object> selectAllUserBaseInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("userList", userService.selectAllUserBaseInfo());
        return modelMap;
    }

}
