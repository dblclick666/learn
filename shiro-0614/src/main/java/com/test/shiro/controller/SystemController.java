package com.test.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {

    @RequestMapping("test.html")
    public ModelAndView test() {
        return new ModelAndView("page", "message", "test");
    }

    @RequestMapping("list.html")
    public ModelAndView list() {
        return new ModelAndView("page", "message", "list");
    }

    @RequestMapping("page.html")
    public ModelAndView page() {
        return new ModelAndView("page", "message", "page");
    }
    @RequestMapping("auth_error.html")
    public ModelAndView error() {
        return new ModelAndView("page", "message", "权限不足");
    }
}
