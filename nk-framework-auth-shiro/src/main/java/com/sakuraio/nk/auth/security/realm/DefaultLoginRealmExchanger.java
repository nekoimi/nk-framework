package com.sakuraio.nk.auth.security.realm;

import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;
import com.sakuraio.nk.auth.api.contract.LoginServiceProvider;
import com.sakuraio.nk.auth.api.contract.LoginToken;
import com.sakuraio.nk.error.Errors;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;

import java.util.List;

/**
 * <p>DefaultLoginRealmExchanger</p>
 * <p>
 * 自定义的登录认证逻辑分配器
 *
 * @author nekoimi 2022/10/13
 */
@Slf4j
public class DefaultLoginRealmExchanger extends AbstractLoginRealmExchanger {
    private final List<LoginServiceProvider> loginProviderList;

    public DefaultLoginRealmExchanger(List<LoginServiceProvider> loginProviderList) {
        this.loginProviderList = loginProviderList;
    }

    @Override
    protected JwtSubject doLogin(LoginToken loginToken) {
        try {
            for (LoginServiceProvider loginProvider : loginProviderList) {
                if (loginProvider.supports(loginToken.getAuthType())) {
                    return loginProvider.doLogin(loginToken.getLoginParam());
                }
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new AuthenticationException(e.getMessage());
        }
        // not supports
        throw new AuthenticationException(Errors.AUTH_TYPE_NOT_SUPPORTS_EXCEPTION.message());
    }
}
