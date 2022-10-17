package com.sakuraio.nk.auth.security.filter;

import com.google.common.collect.Lists;
import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.auth.api.contract.LoginToken;
import com.sakuraio.nk.auth.api.exception.LoginFailedException;
import com.sakuraio.nk.auth.security.wrapper.LoginTokenWrapper;
import com.sakuraio.nk.util.http.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpMethod;
import org.springframework.web.server.MethodNotAllowedException;

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
        log.debug("createToken: 包装登录请求");
        return LoginTokenWrapper.wrapper((HttpServletRequest) request, (HttpServletResponse) response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.debug("isAccessAllowed: 拦截登录请求");
        return !isLoginRequest(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse response) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!HttpMethod.POST.matches(request.getMethod())) {
            throw new MethodNotAllowedException(request.getMethod(), Lists.newArrayList(HttpMethod.POST));
        }
        log.debug("onAccessDenied: 执行登录");
        executeLogin(servletRequest, response);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.debug("onLoginFailure: 登录失败处理");
        loginResultHandler.handleLoginFailure((HttpServletRequest) request, (HttpServletResponse) response, new LoginFailedException(0, e.getMessage()));
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        log.debug("onLoginSuccess: 登录成功处理");
        Object principal = subject.getPrincipal();
        if (principal instanceof JwtSubject) {
            JwtSubject jwtSubject = (JwtSubject) principal;
            loginResultHandler.handleLoginSuccess((HttpServletRequest) request, (HttpServletResponse) response, jwtSubject);
        } else {
            log.error("登录类型[{}]认证结果不是JwtSubject类型实例！", RequestUtils.getAuthType((HttpServletRequest) request));
        }
        return super.onLoginSuccess(token, subject, request, response);
    }
}
