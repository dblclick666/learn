package com.test.shiro.controller;

import com.test.shiro.pojo.AdminUser;
import com.test.shiro.pojo.Menu;
import com.test.shiro.service.AdminUserService;
import com.test.shiro.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("gologin.html")
    public String goLogin() {
        return "login";
    }

    @RequestMapping("login.html")
    public ModelAndView login(String userName, String password) {
//        AdminUser adminUser = adminUserService.doLogin(userName, password);
//        if (adminUser == null) {
//            return new ModelAndView("login", "message", "用户名或密码错误");
//        }
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            List<Menu> menuList = menuService.getUserMenu((Integer) subject.getPrincipal());
            return new ModelAndView("index", "menuList", menuList);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ModelAndView("login", "message", "用户名或密码错误");
        }

    }
}
