package com.test.shiro.service.impl;

import com.test.shiro.dao.RoleDAO;
import com.test.shiro.pojo.Role;
import com.test.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;


    @Override
    public List<Integer> getUserRoleIds(Integer userId) {
        return roleDAO.getUserRoleIds(userId);
    }


    @Override
    public void addUserRole(int userId, int[] roleIds) {
        roleDAO.deleteUserRole(userId);
        Map<String, Integer> param = new HashMap<>();
        param.put("userId", userId);
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        for (int roleId : roleIds) {
            param.put("roleId", roleId);
            roleDAO.addUserRole(param);
        }
    }

    @Override
    public List<Role> getRoleList() {
        return roleDAO.getRoleList();
    }
}
