package com.sakuraio.nk.auth.server.handler;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.contract.LoginResultHandler;
import com.sakuraio.nk.auth.api.exception.LoginFailedException;
import com.sakuraio.nk.auth.server.utils.JwtUtils;
import com.sakuraio.nk.auth.server.vo.LoginResponseVO;
import com.sakuraio.nk.constants.RequestConstants;
import com.sakuraio.nk.core.error.vo.ErrorDetailsVO;
import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.json.jackson.JsonUtils;
import com.sakuraio.nk.util.http.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>DefaultLoginResultHandler</p>
 *
 * @author nekoimi 2022/10/14
 */
public class DefaultLoginResultHandler implements LoginResultHandler {

    @Override
    public void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, LoginFailedException e) {
        ResponseUtils.sendJson(response, JsonUtils.writeValueAsString(
                ErrorDetailsVO.of(e.getCode(), request.getMethod(), request.getRequestURI(), e.getMessage(), e.getTrace())
        ));
    }

    @Override
    public void handleLoginSuccess(HttpServletRequest request, HttpServletResponse response, JwtSubject subject) {
        // 生成jwt token
        String token = JwtUtils.encode(subject);
        // 响应头添加Authorization标识
        response.addHeader(RequestConstants.HEADER_AUTHORIZATION, "Bearer " + token);
        // 输出响应
        ResponseUtils.sendJson(response, JsonUtils.writeValueAsString(
                BaseResponse.ok(LoginResponseVO.of(token, subject))
        ));
    }
}
