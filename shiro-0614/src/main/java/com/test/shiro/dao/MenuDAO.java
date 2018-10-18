package com.test.shiro.dao;

import com.test.shiro.pojo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuDAO {

    List<Menu> getUserMenu(Integer userId);

    List<Integer> getMenuRoles(Integer menuId);

    List<Menu> getMenuList();

    void deleteRoleMenu(int roleId);

    void addRoleMenu(Map<String, Integer> param);
}
