package com.sakuraio.nk.auth.api.config;

import com.sakuraio.nk.auth.api.config.properties.AuthProperties;
import com.sakuraio.nk.auth.api.jwt.JwtUtils;
import com.sakuraio.nk.auth.api.service.JwtSubjectRemoteService;
import com.sakuraio.nk.auth.api.sso.SsoUtils;
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
    private final JwtSubjectRemoteService jwtSubjectService;

    public JwtInitializingPropertiesSet(AuthProperties authProperties, JwtSubjectRemoteService jwtSubjectService) {
        this.properties = authProperties;
        this.jwtSubjectService = jwtSubjectService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JwtUtils.setAlgorithm(properties.getJwt().getSecret());
        JwtUtils.setTokenTTL(properties.getJwt().getTtl());
        JwtUtils.setTokenRefreshTTL(properties.getJwt().getRefreshTtl());

        SsoUtils.setJwtSubjectService(jwtSubjectService);

        log.debug("jwt-PropertiesSet");
    }
}
