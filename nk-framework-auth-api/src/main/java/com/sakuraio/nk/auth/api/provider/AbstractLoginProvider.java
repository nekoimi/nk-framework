package com.sakuraio.nk.auth.api.provider;

import com.sakuraio.nk.auth.api.contract.LoginProvider;

import java.util.Objects;

/**
 * <p>AbstractLoginProvider</p>
 * <p>
 * 实现该登录认证方式的抽象类，以扩展登录认证方式
 *
 * @author nekoimi 2022/10/14
 */
public abstract class AbstractLoginProvider implements LoginProvider {

    @Override
    public boolean supports(String authType) {
        return Objects.equals(getAuthType(), authType);
    }
}
