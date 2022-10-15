package com.sakuraio.nk.auth.api.sso;

import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;
import com.sakuraio.nk.auth.api.jwt.JwtUtils;
import com.sakuraio.nk.auth.api.service.JwtSubjectRemoteService;
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
    private static JwtSubjectRemoteService jwtSubjectService;

    private SsoUtils() {
    }

    public static void setJwtSubjectService(JwtSubjectRemoteService jwtSubjectService) {
        SsoUtils.jwtSubjectService = jwtSubjectService;
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
            String sub = JwtUtils.decodeSub(token);
            BaseResponse<JwtSubject> baseResponse = jwtSubjectService.loadByIdentifier(sub);
            subject = BaseResponseUtils.resolveResponseNonNull(baseResponse);
            SsoContext.setIdentifier(subject.getIdentifier());
            SsoContext.setJwtSubject(subject);
        }
        return subject;
    }
}
