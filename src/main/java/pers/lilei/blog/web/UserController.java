package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.User;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;

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
}
