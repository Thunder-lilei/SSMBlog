package pers.lilei.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lilei.blog.po.Label;
import pers.lilei.blog.service.LabelService;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>SSMBlog</h3>
 * <p>标签Control层</p>
 *
 * @author : 李雷
 * @date : 2021-01-13 15:12
 **/
@Controller
@RequestMapping(value = "/label")
public class LabelController extends BaseController{
    LabelService labelService;
    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }
    @ResponseBody
    @RequestMapping(value = "/addLabelToArticle", method = RequestMethod.POST)
    private Map<String,Object> addLabelToArticle(@RequestBody Label label , Long articleId){
        Map<String,Object> modelMap = new HashMap<>();
        return modelMap;
    }
}
