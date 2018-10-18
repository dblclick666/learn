package com.test.shiro.shiro;

import com.test.shiro.pojo.AdminUser;
import com.test.shiro.service.AdminUserService;
import com.test.shiro.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer userId = (Integer) principalCollection.getPrimaryPrincipal();
        List<Integer> roleIds = roleService.getUserRoleIds(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Integer roleId : roleIds) {
            info.addRole(roleId.toString());
        }
        return info;
    }

    //登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 因为service里已经验证过用户名密码了，所以这里不做任何验证了
        System.out.println("执行了");
        String userName = token.getUsername();
        AdminUser adminUser = adminUserService.getUserByName(userName);
        if (adminUser == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(adminUser.getId(), adminUser.getPassword(), getName());
    }
}
