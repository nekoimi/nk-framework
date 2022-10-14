package com.sakuraio.nk.auth.security.realm;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.contract.LoginProvider;
import com.sakuraio.nk.auth.api.contract.LoginToken;
import com.sakuraio.nk.error.Errors;
import com.sakuraio.nk.error.exception.LoginException;

import java.util.List;

/**
 * <p>DefaultLoginRealmExchanger</p>
 * <p>
 * 自定义的登录认证逻辑分配器
 *
 * @author nekoimi 2022/10/13
 */
public class DefaultLoginRealmExchanger extends AbstractLoginRealmExchanger {
    private final List<LoginProvider> loginProviderList;

    public DefaultLoginRealmExchanger(List<LoginProvider> loginProviderList) {
        this.loginProviderList = loginProviderList;
    }

    @Override
    protected JwtSubject doLogin(LoginToken loginToken) {
        for (LoginProvider loginProvider : loginProviderList) {
            if (loginProvider.supports(loginToken.getAuthType())) {
                return loginProvider.doLogin(loginToken.getLoginParam());
            }
        }
        // not supports
        throw new LoginException(Errors.LOGIN_SUPPORTS_EXCEPTION);
    }
}
