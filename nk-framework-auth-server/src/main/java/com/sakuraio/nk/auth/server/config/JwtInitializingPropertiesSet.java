package com.sakuraio.nk.auth.server.config;

import com.sakuraio.nk.auth.server.config.properties.JwtProperties;
import com.sakuraio.nk.auth.server.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p>JwtInitializingPropertiesSet</p>
 *
 * @author nekoimi 2022/10/15
 */
@Slf4j
public class JwtInitializingPropertiesSet implements InitializingBean {
    private final JwtProperties properties;

    public JwtInitializingPropertiesSet(JwtProperties authProperties) {
        this.properties = authProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JwtUtils.setAlgorithm(properties.getSecret());
        JwtUtils.setTokenTTL(properties.getTtl());
        JwtUtils.setTokenRefreshTTL(properties.getRefreshTtl());
        log.debug("jwt-PropertiesSet");
    }
}
