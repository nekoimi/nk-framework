package com.sakuraio.nk.auth.security.filter;

import com.sakuraio.nk.auth.api.contract.AccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>全局拦截验证Token的Filter</p>
 *
 * @author nekoimi 2022/10/14
 */
@Slf4j
public class ShiroAccessFilter extends AuthorizationFilter {
    private final AccessHandler accessHandler;

    public ShiroAccessFilter(AccessHandler accessHandler) {
        this.accessHandler = accessHandler;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.debug("isAccessAllowed: 拦截所有需要验证的请求");
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        log.debug("onAccessDenied: 检查授权信息");
        accessHandler.handleAccess((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        return true;
    }
}
