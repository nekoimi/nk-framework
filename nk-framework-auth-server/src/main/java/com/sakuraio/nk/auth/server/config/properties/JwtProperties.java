package com.sakuraio.nk.auth.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>AuthProperties</p>
 *
 * @author nekoimi 2022/10/13
 */
@Data
@ConfigurationProperties(prefix = "nk.auth.jwt")
public class JwtProperties {

    /**
     * <p>加密秘钥</p>
     */
    private String secret = "123456";

    /**
     * <p>token有效期，单位秒</p>
     */
    private Integer ttl = 7200;

    /**
     * <p>token刷新有效期，单位秒</p>
     */
    private Integer refreshTtl = 604800;
}
