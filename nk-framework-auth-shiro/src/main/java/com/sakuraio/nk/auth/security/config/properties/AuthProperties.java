package com.sakuraio.nk.auth.security.config.properties;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

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
     * <p>不需要校验token的url路径列表</p>
     */
    private List<String> permitAll = Lists.newArrayList();

    public String getLoginPath() {
        return "/" + StringUtils.removeStart(loginPath, "/");
    }

    public String getLogoutPath() {
        return "/" + StringUtils.removeStart(logoutPath, "/");
    }
}
