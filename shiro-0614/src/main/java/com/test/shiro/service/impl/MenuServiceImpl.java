package com.test.shiro.service.impl;

import com.test.shiro.dao.MenuDAO;
import com.test.shiro.pojo.Menu;
import com.test.shiro.service.MenuService;
import com.test.shiro.shiro.MyShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Override
    public List<Menu> getUserMenu(Integer userId) {
        return menuDAO.getUserMenu(userId);
    }

    @Override
    public List<Integer> getMenuRoles(Integer menuId) {
        return menuDAO.getMenuRoles(menuId);
    }

    @Override
    public List<Menu> getMenuList() {
        return menuDAO.getMenuList();
    }

    @Autowired
    private MyShiroFilterFactoryBean myShiroFilterFactoryBean;

    @Override
    public void addRoleMenu(int roleId, int[] menuIds) {
        menuDAO.deleteRoleMenu(roleId);
        if (menuIds == null || menuIds.length == 0) {
            return;
        }
        Map<String, Integer> param = new HashMap<>();
        param.put("roleId", roleId);
        for (int menuId : menuIds) {
            param.put("menuId", menuId);
            menuDAO.addRoleMenu(param);
        }
        myShiroFilterFactoryBean.setNewPermissions();
    }
}
