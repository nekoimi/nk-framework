package com.sakuraio.nk.auth.security.filter;

import com.sakuraio.nk.util.http.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>全局拦截验证Token的Filter</p>
 *
 * @author nekoimi 2022/10/14
 */
@Slf4j
public class ShiroAccessFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.debug("ShiroLoginFilter->isAccessAllowed: 拦截所有需要验证的请求");
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse response) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = RequestUtils.getToken(request);
        // TODO 验证Jwt Token是否合法
        log.debug("token: {}", token);
        return true;
    }
}
