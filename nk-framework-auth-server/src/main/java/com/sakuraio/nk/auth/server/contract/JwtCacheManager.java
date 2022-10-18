package com.sakuraio.nk.auth.server.contract;

/**
 * <p>JwtCacheManager</p>
 * <p>
 * jwt缓存
 *
 * @author nekoimi 2022/10/15
 */
public interface JwtCacheManager {

    /**
     * <p>并发刷新缓存</p>
     *
     * @param expireToken    过期的token
     * @param refreshedToken 刷新的新token
     */
    void setRefresh(String expireToken, String refreshedToken);

    /**
     * <p>使用过期的token换取已经刷新好的token</p>
     *
     * @param expireToken
     * @return
     */
    String getRefreshedToken(String expireToken);

    /**
     * <p>加入token黑名单</p>
     *
     * @param token
     */
    void addBlack(String token);

    /**
     * <p>token是否被拉黑</p>
     *
     * @param token
     * @return
     */
    Boolean isBlack(String token);
}
