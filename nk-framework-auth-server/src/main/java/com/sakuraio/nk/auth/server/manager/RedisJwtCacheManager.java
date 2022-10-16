package com.sakuraio.nk.auth.server.manager;

import com.sakuraio.nk.auth.server.contract.JwtCacheManager;

/**
 * <p>RedisJwtCacheManager</p>
 *
 * @author nekoimi 2022/10/16
 */
public class RedisJwtCacheManager implements JwtCacheManager {
    @Override
    public void setRefresh(String expireToken, String refreshedToken) {

    }

    @Override
    public String getRefreshedToken(String expireToken) {
        return null;
    }

    @Override
    public void blackAdd(String token) {

    }

    @Override
    public boolean blackHas(String token) {
        return false;
    }
}
