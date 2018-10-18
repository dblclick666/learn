package com.test.shiro.controller;

import com.test.shiro.pojo.Role;
import com.test.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user_role.html")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(params = "act=go_edit")
    public String goAddRole(Model model) {
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);
        return "user_role";
    }

    @RequestMapping(params = "act=add")
    public String addRole(int userId, int[] roleIds) {
        roleService.addUserRole(userId, roleIds);
        return "forward:user_role.html?act=go_edit";
    }

}
