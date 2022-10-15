package com.sakuraio.nk.auth.security.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sakuraio.nk.auth.api.config.properties.AuthProperties;
import com.sakuraio.nk.auth.api.contract.AccessHandler;
import com.sakuraio.nk.auth.api.contract.LoginServiceProvider;
import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.auth.security.factory.DefaultJwtSubjectFactory;
import com.sakuraio.nk.auth.security.filter.ShiroAccessFilter;
import com.sakuraio.nk.auth.security.filter.ShiroLoginFilter;
import com.sakuraio.nk.auth.security.realm.DefaultLoginRealmExchanger;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.List;
import java.util.Map;

/**
 * <p>ShiroConfiguration</p>
 *
 * @author nekoimi 2022/10/13
 */
public class ShiroConfiguration {
    private static final String AUTH_LOGIN = "authLogin";
    private static final String AUTH_ACCESS = "authAccess";
    private static final String UNAUTHENTICATED = "/unauthenticated";

    @Bean
    public SubjectFactory subjectFactory() {
        return new DefaultJwtSubjectFactory();
    }

    @Bean
    @ConditionalOnBean(LoginServiceProvider.class)
    public Realm realm(ObjectProvider<List<LoginServiceProvider>> listObjectProvider) {
        return new DefaultLoginRealmExchanger(listObjectProvider.getIfAvailable());
    }

    @Bean
    @ConditionalOnBean(LoginServiceProvider.class)
    public DefaultWebSecurityManager webSecurityManager(Realm realm, SubjectFactory subjectFactory) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setRealm(realm);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    @ConditionalOnBean(LoginServiceProvider.class)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            AuthProperties authProperties,
            DefaultWebSecurityManager securityManager,
            LoginResultHandler loginResultHandler,
            AccessHandler accessHandler) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl(authProperties.getLoginPath());
        filterFactoryBean.setUnauthorizedUrl(UNAUTHENTICATED);
        filterFactoryBean.setSuccessUrl(UNAUTHENTICATED);
        // 添加Filter配置
        filterFactoryBean.setGlobalFilters(Lists.newArrayList());
        Map<String, Filter> filterMap = Maps.newHashMap();
        filterMap.put(DefaultFilter.anon.name(), DefaultFilter.anon.newInstance());
        filterMap.put(DefaultFilter.logout.name(), DefaultFilter.logout.newInstance());
        // 添加自定义的拦截JwtFilter
        filterMap.put(AUTH_LOGIN, new ShiroLoginFilter(loginResultHandler));
        filterMap.put(AUTH_ACCESS, new ShiroAccessFilter(accessHandler));
        filterFactoryBean.setFilters(filterMap);
        Map<String, String> filterRuleMap = Maps.newHashMap();
        filterRuleMap.put(authProperties.getLoginPath(), AUTH_LOGIN);
        filterRuleMap.put(authProperties.getLogoutPath(), DefaultFilter.logout.name());
        filterRuleMap.put("/**", AUTH_ACCESS);
        filterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return filterFactoryBean;
    }
}
