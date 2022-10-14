package com.sakuraio.nk.auth.security.filter;

import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.auth.api.contract.LoginToken;
import com.sakuraio.nk.auth.security.wrapper.LoginTokenWrapper;
import com.sakuraio.nk.error.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>登录生成Token的Filter</p>
 * <p>
 * 接受登录认证请求，包装登录请求为统一的 {@link LoginToken} 对象
 *
 * @author nekoimi 2022/10/14
 */
@Slf4j
public class ShiroLoginFilter extends AuthenticatingFilter {
    private final LoginResultHandler loginResultHandler;

    public ShiroLoginFilter(LoginResultHandler loginResultHandler) {
        this.loginResultHandler = loginResultHandler;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        log.debug("ShiroLoginFilter->createToken: 包装登录请求");
        return LoginTokenWrapper.wrapper((HttpServletRequest) request, (HttpServletResponse) response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.debug("ShiroLoginFilter->isAccessAllowed: 拦截登录请求");
        return !isLoginRequest(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.debug("ShiroLoginFilter->onAccessDenied: 执行登录");
        executeLogin(request, response);
        return true;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.debug("ShiroLoginFilter->onLoginFailure: 登录失败处理");
        loginResultHandler.handleLoginFailure(
                (HttpServletRequest) request,
                (HttpServletResponse) response,
                new LoginException(0, e.getMessage())
        );
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        log.debug("ShiroLoginFilter->onLoginSuccess: 登录成功处理");
        loginResultHandler.handleLoginSuccess(
                (HttpServletRequest) request,
                (HttpServletResponse) response,
                null
        );
        return super.onLoginSuccess(token, subject, request, response);
    }
}
