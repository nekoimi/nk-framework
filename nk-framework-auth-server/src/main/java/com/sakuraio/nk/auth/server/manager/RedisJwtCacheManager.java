package com.sakuraio.nk.auth.server.manager;

import com.sakuraio.nk.auth.server.contract.JwtCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * <p>RedisJwtCacheManager</p>
 *
 * @author nekoimi 2022/10/16
 */
@Slf4j
public class RedisJwtCacheManager implements JwtCacheManager {
    /**
     * <p>刷新置换并发缓存：300秒，默认保存5分钟</p>
     */
    private static final int TOKEN_REFRESH_CONCURRENT_EXPIRE = 300;

    /**
     * <p>token刷新黑名单</p>
     */
    private static final String TOKEN_REFRESHED_BLACK = "TOKEN_REFRESHED_BLACK";

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisJwtCacheManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setRefresh(String expireToken, String refreshedToken) {
        log.debug("token并发刷新缓存: {}: {}", expireToken, refreshedToken);
        redisTemplate.opsForValue().set(expireToken, refreshedToken, Duration.ofSeconds(TOKEN_REFRESH_CONCURRENT_EXPIRE));
    }

    @Override
    public String getRefreshedToken(String expireToken) {
        String refreshedToken = (String) redisTemplate.opsForValue().get(expireToken);
        log.debug("置换刷新token: {}: {}", expireToken, refreshedToken);
        return refreshedToken;
    }

    @Override
    public void addBlack(String token) {
        log.debug("添加token到黑名单: {}", token);
        redisTemplate.opsForSet().add(TOKEN_REFRESHED_BLACK, token);
    }

    @Override
    public Boolean isBlack(String token) {
        Boolean isBlack = redisTemplate.opsForSet().isMember(TOKEN_REFRESHED_BLACK, token);
        log.debug("token是否已经刷新过: {}: {}", token, isBlack);
        return isBlack;
    }
}
