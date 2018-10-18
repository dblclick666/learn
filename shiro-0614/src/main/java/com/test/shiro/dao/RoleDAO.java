package com.test.shiro.dao;

import com.test.shiro.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleDAO {



    List<Integer> getUserRoleIds(Integer userId);

    void deleteUserRole(int userId);

    void addUserRole(Map<String, Integer> param);

    List<Role> getRoleList();
}
