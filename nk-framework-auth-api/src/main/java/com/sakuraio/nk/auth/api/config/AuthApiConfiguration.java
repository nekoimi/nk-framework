package com.sakuraio.nk.auth.api.config;

import com.sakuraio.nk.auth.api.contract.AccessHandler;
import com.sakuraio.nk.auth.api.handler.DefaultAccessHandler;
import com.sakuraio.nk.auth.api.service.AuthRemoteService;
import com.sakuraio.nk.auth.api.sso.SsoUtils;
import org.springframework.context.annotation.Bean;

/**
 * <p>AuthApiConfiguration</p>
 *
 * @author nekoimi 2022/10/13
 */
public class AuthApiConfiguration {

    @Bean
    public AccessHandler accessHandler(AuthRemoteService authRemoteService) {
        SsoUtils.setAuthService(authRemoteService);
        return new DefaultAccessHandler(authRemoteService);
    }
}
