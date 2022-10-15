package com.sakuraio.nk.auth.api.config.properties;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>AuthProperties</p>
 *
 * @author nekoimi 2022/10/13
 */
@Data
@ConfigurationProperties(prefix = "nk.auth")
public class AuthProperties {
    private static final String DEFAULT_LOGIN_PATH = "api/auth/login";
    private static final String DEFAULT_LOGOUT_PATH = "api/auth/logout";
    /**
     * <p>认证API地址</p>
     */
    private String loginPath = DEFAULT_LOGIN_PATH;

    /**
     * <p>注销API地址</p>
     */
    private String logoutPath = DEFAULT_LOGOUT_PATH;

    /**
     * <p>jwt属性配置</p>
     */
    private Jwt jwt = new Jwt();

    @Data
    public static class Jwt {
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

    public String getLoginPath() {
        return "/" + StringUtils.removeStart(loginPath, "/");
    }

    public String getLogoutPath() {
        return "/" + StringUtils.removeStart(logoutPath, "/");
    }
}
