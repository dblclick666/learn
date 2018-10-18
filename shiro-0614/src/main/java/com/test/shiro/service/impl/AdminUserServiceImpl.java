package com.test.shiro.service.impl;

import com.test.shiro.dao.AdminUserDAO;
import com.test.shiro.pojo.AdminUser;
import com.test.shiro.service.AdminUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDAO adminUserDAO;

    @Override
    public AdminUser doLogin(String userName, String password) {
        AdminUser adminUser = adminUserDAO.getUserByName(userName);
        if (adminUser == null || !adminUser.getPassword().equals(password)) {
            return null;
        }
        return adminUser;
    }

    @Override
    public AdminUser getUserByName(String userName) {
        return adminUserDAO.getUserByName(userName);
    }
}
