package com.sakuraio.nk.auth.api.config;

import com.sakuraio.nk.auth.api.config.properties.AuthProperties;
import com.sakuraio.nk.auth.api.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p>JwtInitializingPropertiesSet</p>
 *
 * @author nekoimi 2022/10/15
 */
@Slf4j
public class JwtInitializingPropertiesSet implements InitializingBean {
    private final AuthProperties properties;

    public JwtInitializingPropertiesSet(AuthProperties authProperties) {
        this.properties = authProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JwtUtils.setAlgorithm(properties.getJwt().getSecret());
        JwtUtils.setTokenTTL(properties.getJwt().getTtl());
        JwtUtils.setTokenRefreshTTL(properties.getJwt().getRefreshTtl());
        log.debug("jwt-PropertiesSet");
    }
}
