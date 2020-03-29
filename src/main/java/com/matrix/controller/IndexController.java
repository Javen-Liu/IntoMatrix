package com.matrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/29 22:26
 * @github https://github.com/Javen-Liu
 * 首页内容控制器
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){

        return modelAndView;
    }
}
