package com.test.shiro.service;

import com.test.shiro.pojo.AdminUser;

public interface AdminUserService {

    AdminUser doLogin(String userName, String password);

    AdminUser getUserByName(String userName);
}
