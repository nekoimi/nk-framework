package com.sakuraio.nk.auth.api.provider;

import com.sakuraio.nk.auth.api.contract.LoginServiceProvider;

import java.util.Objects;

/**
 * <p>AbstractLoginServiceProvider</p>
 * <p>
 * 实现该登录认证方式的抽象类，以扩展登录认证方式
 *
 * @author nekoimi 2022/10/14
 */
public abstract class AbstractLoginServiceProvider implements LoginServiceProvider {

    @Override
    public boolean supports(String authType) {
        return Objects.equals(getAuthType(), authType);
    }
}
