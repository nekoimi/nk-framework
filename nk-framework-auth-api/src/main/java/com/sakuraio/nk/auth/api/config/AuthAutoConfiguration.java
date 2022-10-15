package com.sakuraio.nk.auth.api.config;

import com.sakuraio.nk.auth.api.config.properties.AuthProperties;
import com.sakuraio.nk.auth.api.contract.AccessHandler;
import com.sakuraio.nk.auth.api.contract.jwt.JwtCacheManager;
import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.auth.api.handler.DefaultAccessHandler;
import com.sakuraio.nk.auth.api.handler.DefaultLoginResultHandler;
import com.sakuraio.nk.auth.api.jwt.RedisJwtCacheManager;
import com.sakuraio.nk.auth.api.service.JwtSubjectRemoteService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>AuthAutoConfiguration</p>
 *
 * @author nekoimi 2022/10/13
 */
@EnableConfigurationProperties(
        AuthProperties.class
)
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(LoginResultHandler.class)
    public LoginResultHandler loginResultHandler() {
        return new DefaultLoginResultHandler();
    }

    @Bean
    public JwtCacheManager jwtCacheManager() {
        return new RedisJwtCacheManager();
    }

    @Bean
    @ConditionalOnMissingBean(AccessHandler.class)
    public AccessHandler accessHandler(JwtCacheManager cacheManager, JwtSubjectRemoteService jwtSubjectService) {
        return new DefaultAccessHandler(cacheManager, jwtSubjectService);
    }

    @Bean
    public JwtInitializingPropertiesSet jwtInitializingPropertiesSet(
            AuthProperties authProperties, JwtSubjectRemoteService jwtSubjectService) {
        return new JwtInitializingPropertiesSet(authProperties, jwtSubjectService);
    }
}
