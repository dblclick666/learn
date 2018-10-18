package com.test.shiro.service;

import com.test.shiro.pojo.Role;

import java.util.List;

public interface RoleService {

    List<Integer> getUserRoleIds(Integer userId);

    void addUserRole(int userId, int[] roleIds);

    List<Role> getRoleList();
}
