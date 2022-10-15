package com.sakuraio.nk.auth.api.contract;

import com.sakuraio.nk.auth.api.exception.LoginFailedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>LoginFailureHandler</p>
 * <p>
 * 登录认证回调处理
 *
 * @author nekoimi 2022/10/14
 */
public interface LoginResultHandler {

    /**
     * <p>登录认证失败处理</p>
     *
     * @param request
     * @param response
     * @param e
     */
    void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, LoginFailedException e);

    /**
     * <p>登录认证成功回调</p>
     *
     * @param request
     * @param response
     * @param subject
     */
    void handleLoginSuccess(HttpServletRequest request, HttpServletResponse response, JwtSubject subject);
}
