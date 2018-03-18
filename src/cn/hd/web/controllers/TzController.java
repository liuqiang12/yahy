package cn.hd.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转
 */
@Controller
public class TzController extends BaseController{
    @RequestMapping("hygl/index")
    public String hygl(){
        return hyglPrefix+"index";
    }
}
