package com.sakuraio.nk.auth.api.config;

import com.sakuraio.nk.auth.api.config.properties.AuthProperties;
import com.sakuraio.nk.auth.api.contract.AccessHandler;
import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.auth.api.handler.DefaultAccessHandler;
import com.sakuraio.nk.auth.api.handler.DefaultLoginResultHandler;
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
    @ConditionalOnMissingBean(AccessHandler.class)
    public AccessHandler accessHandler() {
        return new DefaultAccessHandler();
    }

}
