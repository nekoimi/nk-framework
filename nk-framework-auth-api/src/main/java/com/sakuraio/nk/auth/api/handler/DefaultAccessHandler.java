package com.sakuraio.nk.auth.api.handler;

import com.sakuraio.nk.auth.api.contract.AccessHandler;
import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.service.AuthRemoteService;
import com.sakuraio.nk.auth.api.sso.SsoContext;
import com.sakuraio.nk.auth.api.vo.AccessResponseVO;
import com.sakuraio.nk.constants.RequestConstants;
import com.sakuraio.nk.core.error.exception.RequestValidationException;
import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.core.utils.BaseResponseUtils;
import com.sakuraio.nk.util.http.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>DefaultAccessHandler</p>
 * <p>
 * 请求授权验证，当前验证框架主要验证token是否合法，初始化SsoContext的认证信息
 *
 * @author nekoimi 2022/10/15
 */
@Slf4j
public class DefaultAccessHandler implements AccessHandler {
    private final AuthRemoteService authService;

    public DefaultAccessHandler(AuthRemoteService authRemoteService) {
        this.authService = authRemoteService;
    }

    @Override
    public synchronized void handleAccess(HttpServletRequest request, HttpServletResponse response) {
        String token = RequestUtils.getToken(request);
        if (StringUtils.isBlank(token)) {
            throw new RequestValidationException("缺少token值");
        }
        BaseResponse<AccessResponseVO> baseResponse = authService.authorization(token);
        AccessResponseVO accessResponseVO = BaseResponseUtils.resolveResponseNonNull(baseResponse);

        String accessToken = accessResponseVO.getAccessToken();
        JwtSubject subject = accessResponseVO.getSubject();
        Boolean needRefresh = accessResponseVO.getNeedRefresh();

        if (Boolean.TRUE.equals(needRefresh)) {
            // 添加刷新token响应头
            response.addHeader(RequestConstants.HEADER_AUTHORIZATION, "Bearer " + accessToken);
        }

        // 保存认证上下文
        SsoContext.setToken(accessToken);
        SsoContext.setIdentifier(subject.getIdentifier());
        SsoContext.setJwtSubject(subject);

        log.debug("SsoContext: {}", subject.getIdentifier());
    }
}
