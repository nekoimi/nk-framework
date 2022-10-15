package com.sakuraio.nk.auth.api.sso;

import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;

/**
 * <p>SsoUtils</p>
 * <p>
 * 统一认证信息工具类
 *
 * @author nekoimi 2022/10/14
 */
public class SsoUtils {

    private SsoUtils() {
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
        return SsoContext.currentJwtSubject();
    }
}
