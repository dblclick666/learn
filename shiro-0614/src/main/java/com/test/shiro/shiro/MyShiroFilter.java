package com.test.shiro.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyShiroFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        //MyShiroFilterFactoryBean的setFilterChainDefinitions方法里，
        //把每个菜单对应的角色都加载了
        //所以这里的roleArray就是当前可以访问这个url的所有角色
        String[] roleArray = (String[]) o;
        if (roleArray == null || roleArray.length == 0) {
            return true;
        }
        for (int i = 0; i < roleArray.length; i++) {
            //只要用户由任意一个角色，就可以访问成功
            if (subject.hasRole(roleArray[i])) {
                return true;
            }
        }

        return false;
    }
}
