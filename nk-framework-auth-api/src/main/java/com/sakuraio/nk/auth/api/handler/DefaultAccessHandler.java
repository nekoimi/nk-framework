package com.sakuraio.nk.auth.api.handler;

import com.sakuraio.nk.auth.api.contract.AccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>DefaultAccessHandler</p>
 * <p>
 * 请求授权验证，当前验证框架主要验证token是否合法
 *
 * @author nekoimi 2022/10/15
 */
public class DefaultAccessHandler implements AccessHandler {

    @Override
    public void handleAccess(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("X-Test-Value", "abc123456");
    }
}
