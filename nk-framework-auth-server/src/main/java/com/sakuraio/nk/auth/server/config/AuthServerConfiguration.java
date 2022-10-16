package com.sakuraio.nk.auth.server.config;

import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.auth.api.service.AuthRemoteService;
import com.sakuraio.nk.auth.server.config.properties.JwtProperties;
import com.sakuraio.nk.auth.server.contract.JwtCacheManager;
import com.sakuraio.nk.auth.server.handler.DefaultLoginResultHandler;
import com.sakuraio.nk.auth.server.manager.RedisJwtCacheManager;
import com.sakuraio.nk.auth.server.service.AuthService;
import com.sakuraio.nk.auth.server.service.JwtSubjectService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>AuthServerConfiguration</p>
 *
 * @author nekoimi 2022/10/16
 */
@EnableConfigurationProperties(
        JwtProperties.class
)
public class AuthServerConfiguration {

    @Bean
    public LoginResultHandler loginResultHandler() {
        return new DefaultLoginResultHandler();
    }

    @Bean
    public JwtCacheManager jwtCacheManager() {
        return new RedisJwtCacheManager();
    }

    @Bean
    public JwtInitializingPropertiesSet jwtInitializingPropertiesSet(JwtProperties authProperties) {
        return new JwtInitializingPropertiesSet(authProperties);
    }

    @Bean
    public AuthRemoteService authRemoteService(JwtCacheManager cacheManager, JwtSubjectService jwtSubjectService) {
        return new AuthService(cacheManager, jwtSubjectService);
    }
}
