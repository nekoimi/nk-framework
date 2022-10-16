package com.sakuraio.nk.auth.security.wrapper;

import cn.hutool.core.lang.Dict;
import com.sakuraio.nk.auth.api.contract.LoginToken;
import com.sakuraio.nk.json.jackson.JsonUtils;
import com.sakuraio.nk.util.http.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>LoginTokenWrapper</p>
 * <p>
 * {@link AuthenticationToken} 包装类 {@link LoginToken}
 *
 * @author nekoimi 2022/10/14
 */
@Slf4j
public class LoginTokenWrapper implements AuthenticationToken, LoginToken {
    private final String authType;
    private final Dict loginParam;

    public LoginTokenWrapper(HttpServletRequest request, HttpServletResponse response) {
        this.authType = RequestUtils.getAuthType(request);
        // 合并 queryString、body
        this.loginParam = Dict.create();
        // query
        this.loginParam.putAll(RequestUtils.getQueryDict(request.getQueryString()));
        // body
        try {
            String jsonString = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            if (StringUtils.isNotBlank(jsonString)) {
                Map map = JsonUtils.readValue(jsonString, Map.class);
                this.loginParam.putAll(map);
            }
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            log.error(e.getMessage(), e);
            log.error("读取登录认证请求error！{}", e.getMessage());
        }
    }

    /**
     * <p>wrapper</p>
     *
     * @param request
     * @param response
     * @return
     */
    public static LoginTokenWrapper wrapper(HttpServletRequest request, HttpServletResponse response) {
        return new LoginTokenWrapper(request, response);
    }

    @Override
    public String getAuthType() {
        return this.authType;
    }

    @Override
    public Dict getLoginParam() {
        return this.loginParam;
    }

    @Override
    public Object getPrincipal() {
        return this.loginParam;
    }

    @Override
    public Object getCredentials() {
        return this.loginParam;
    }

    @Override
    public String toString() {
        return JsonUtils.writeValueAsString(this);
    }
}
