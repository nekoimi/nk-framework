package com.sakuraio.nk.auth.api.sso;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.service.AuthRemoteService;
import com.sakuraio.nk.auth.api.vo.AccessResponseVO;
import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.core.utils.BaseResponseUtils;

/**
 * <p>SsoUtils</p>
 * <p>
 * 统一认证信息工具类
 *
 * @author nekoimi 2022/10/14
 */
public class SsoUtils {
    private static AuthRemoteService authService;

    private SsoUtils() {
    }

    public static void setAuthService(AuthRemoteService authRemoteService) {
        SsoUtils.authService = authRemoteService;
    }

    /**
     * <p>获取当前上下文认证对象唯一标识</p>
     *
     * @return
     */
    public static String getIdentifier() {
        return SsoContext.currentIdentifier();
    }

    /**
     * <p>获取用户名称</p>
     *
     * @return
     */
    public static String getUsername() {
        JwtSubject jwtSubject = SsoContext.currentJwtSubject();
        return jwtSubject == null ? null : jwtSubject.getUsername();
    }

    /**
     * <p>获取当前上下文认证对象</p>
     *
     * @return
     */
    public static JwtSubject getSubject() {
        JwtSubject subject = SsoContext.currentJwtSubject();
        if (subject == null) {
            String token = SsoContext.currentToken();
            BaseResponse<AccessResponseVO> baseResponse = authService.authorization(token);
            AccessResponseVO accessResponseVO = BaseResponseUtils.resolveResponseNonNull(baseResponse);
            String accessToken = accessResponseVO.getAccessToken();
            subject = accessResponseVO.getSubject();
            SsoContext.setToken(accessToken);
            SsoContext.setIdentifier(subject.getIdentifier());
            SsoContext.setJwtSubject(subject);
        }
        return subject;
    }
}
