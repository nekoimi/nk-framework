package com.sakuraio.nk.auth.security.realm;

import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;
import com.sakuraio.nk.auth.api.contract.LoginToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;

/**
 * <p>AbstractLoginRealmExchanger</p>
 *
 * @author nekoimi 2022/10/14
 */
@Slf4j
public abstract class AbstractLoginRealmExchanger implements Realm {
    private static final String NAME = "loginRealmExchanger";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        log.debug("LoginRealmExchanger->supports: {} instanceof LoginToken", token.getClass());
        return token instanceof LoginToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("LoginRealmExchanger->getAuthenticationInfo: {}", token);
        LoginToken loginToken = (LoginToken) token;
        JwtSubject jwtSubject = doLogin(loginToken);
        return new SimpleAuthenticationInfo(jwtSubject, jwtSubject, NAME);
    }

    /**
     * <p>执行登录认证逻辑</p>
     *
     * @param loginToken 登录认证Token
     * @return
     */
    protected abstract JwtSubject doLogin(LoginToken loginToken);
}
