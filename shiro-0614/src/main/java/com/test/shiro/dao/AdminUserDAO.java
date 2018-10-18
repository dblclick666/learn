package com.test.shiro.dao;

import com.test.shiro.pojo.AdminUser;

public interface AdminUserDAO {

    AdminUser getUserByName(String userName);
}
