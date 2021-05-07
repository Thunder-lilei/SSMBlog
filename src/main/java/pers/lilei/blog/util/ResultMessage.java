package pers.lilei.blog.util;

import pers.lilei.blog.constant.MessageConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>返回信息、</p>
 *
 * @author : 李雷
 * @date : 2021-05-07 19:26
 **/
public class ResultMessage {
    public static Map<String,Object> successMessage() {
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        return modelMap;
    }
    public static <T> Map<String,Object> successMessageResult(String resultName, T result) {
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
        modelMap.put(resultName, result);
        return modelMap;
    }
    public static Map<String,Object> successMessageBoolean(Boolean result) {
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, result);
        return modelMap;
    }
    public static Map<String,Object> waringMessage(String message) {
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put(MessageConstant.MESSAGE, message);
        return modelMap;
    }
}
