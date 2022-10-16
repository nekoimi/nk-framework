package com.sakuraio.nk.auth.api.provider;

import com.sakuraio.nk.auth.api.contract.LoginServiceProvider;

import java.util.Objects;

/**
 * <p>AbstractLoginServiceProvider</p>
 * <p>
 * 继承该抽象类，以扩展登录认证类型
 *
 * @author nekoimi 2022/10/14
 */
public abstract class AbstractLoginServiceProvider implements LoginServiceProvider {

    @Override
    public boolean supports(String authType) {
        return Objects.equals(getAuthType(), authType);
    }
}
