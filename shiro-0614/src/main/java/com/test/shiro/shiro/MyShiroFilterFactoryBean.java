package com.test.shiro.shiro;

import com.test.shiro.pojo.Menu;
import com.test.shiro.service.MenuService;
import com.test.shiro.service.RoleService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {
    public static final String ROLE_STRING = "roles[{0}]";
    @Autowired
    private MenuService menuService;
    private static String filterChainDefinitions = "";

    @Override
    public void setFilterChainDefinitions(String definitions) {
        filterChainDefinitions = definitions;
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }
                List<Menu> menuList = menuService.getMenuList();
                for (Menu menu : menuList) {
                    if (!StringUtils.isEmpty(menu.getUrl())) {
                        List<Integer> roleIds = menuService.getMenuRoles(menu.getId());
                        StringBuilder sb = new StringBuilder();
                        for (Integer roleId : roleIds) {
                            sb.append(roleId).append(",");
                        }
                        //去掉逗号
                        String str = sb.substring(0, sb.length() - 1);
                        //验证时不匹配参数
                        if (menu.getUrl().indexOf("?") >= 0) {
                            section.put(menu.getUrl().substring(0, menu.getUrl().indexOf("?")),
                                    MessageFormat.format(ROLE_STRING, str));
                        } else {
                            section.put(menu.getUrl(), MessageFormat.format(ROLE_STRING, str));
                }
            }
        }
        section.put("/**", "authc");//最后写这句，所有的路径都要验证
        this.setFilterChainDefinitionMap(section);
    }

    public void setNewPermissions() {
        //setFilterChainDefinitions(filterChainDefinitions);
        synchronized (this) {   //强制同步，控制线程安全
            try {
                AbstractShiroFilter shiroFilter = (AbstractShiroFilter) this.getObject();
                PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
                        .getFilterChainResolver();
                // 过滤管理器
                DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
                // 清除权限配置
                manager.getFilterChains().clear();
                this.getFilterChainDefinitionMap().clear();
                // 重新设置权限
                this.setFilterChainDefinitions(MyShiroFilterFactoryBean.filterChainDefinitions);//传入配置中的filterchains
                Map<String, String> chains = this.getFilterChainDefinitionMap();
                //重新生成过滤链
                if (!CollectionUtils.isEmpty(chains)) {
                    for (Map.Entry<String, String> chain : chains.entrySet()) {
                        manager.createChain(chain.getKey(), chain.getValue().replace(" ", ""));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
