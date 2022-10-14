package com.sakuraio.nk.auth.api.handler;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.error.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>DefaultLoginResultHandler</p>
 *
 * @author nekoimi 2022/10/14
 */
public class DefaultLoginResultHandler implements LoginResultHandler {

    @Override
    public void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, LoginException e) {

    }

    @Override
    public void handleLoginSuccess(HttpServletRequest request, HttpServletResponse response, JwtSubject subject) {

    }
}
