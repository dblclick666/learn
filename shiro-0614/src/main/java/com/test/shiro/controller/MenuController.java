package com.test.shiro.controller;

import com.test.shiro.pojo.Menu;
import com.test.shiro.pojo.Role;
import com.test.shiro.service.MenuService;
import com.test.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role_menu.html")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(params = "act=go_edit")
    public String goAddRole(Model model) {
        List<Menu> menuList = menuService.getMenuList();
        model.addAttribute("menuList", menuList);
        return "role_menu";
    }

    @RequestMapping(params = "act=add")
    public String addRole(int roleId, int[] menuIds) {
        menuService.addRoleMenu(roleId, menuIds);
        return "forward:role_menu.html?act=go_edit";
    }

}
