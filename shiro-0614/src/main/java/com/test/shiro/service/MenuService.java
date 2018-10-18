package com.test.shiro.service;

import com.test.shiro.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getUserMenu(Integer userId);

    List<Integer> getMenuRoles(Integer menuId);

    List<Menu> getMenuList();

    void addRoleMenu(int roleId, int[] menuIds);
}
