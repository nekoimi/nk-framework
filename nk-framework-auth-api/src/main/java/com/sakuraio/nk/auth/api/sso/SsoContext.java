package com.sakuraio.nk.auth.api.sso;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;
import com.sakuraio.nk.auth.api.contract.JwtSubject;

import java.util.Map;

/**
 * <p>SsoContext</p>
 * <p>
 * 全局认证信息上下文
 * <p>
 * https://github.com/alibaba/transmittable-thread-local
 *
 * @author nekoimi 2022/10/14
 */
public class SsoContext {
    private static final String IDENTIFIER_KEY = "identifier";
    private static final String TOKEN_KEY = "token";
    private static final String JWT_SUBJECT_KEY = "jwtSubject";
    private static final TransmittableThreadLocal<Map<String, Object>> context = new TransmittableThreadLocal<>();

    private SsoContext() {
    }

    /**
     * <p>获取当前上下文认证对象唯一标识</p>
     *
     * @return
     */
    public static String currentIdentifier() {
        return (String) getValue(IDENTIFIER_KEY);
    }

    /**
     * <p>保存唯一标识</p>
     *
     * @param identifierValue
     */
    public static void setIdentifier(String identifierValue) {
        setValue(IDENTIFIER_KEY, identifierValue);
    }

    /**
     * <p>获取当前上下文权限token</p>
     *
     * @return
     */
    public static String currentToken() {
        return (String) getValue(TOKEN_KEY);
    }

    /**
     * <p>保存Token</p>
     *
     * @param tokenValue
     */
    public static void setToken(String tokenValue) {
        setValue(TOKEN_KEY, tokenValue);
    }

    /**
     * <p>获取当前上下文认证对象信息</p>
     *
     * @return
     */
    public static JwtSubject currentJwtSubject() {
        Object value = getValue(JWT_SUBJECT_KEY);
        return value == null ? null : (JwtSubject) value;
    }

    /**
     * <p>保存认证对象</p>
     *
     * @param subject
     */
    public static void setJwtSubject(JwtSubject subject) {
        setValue(JWT_SUBJECT_KEY, subject);
    }

    /**
     * <p>获取值</p>
     *
     * @param key 键
     * @return
     */
    private static Object getValue(String key) {
        Map<String, Object> contextMap = context.get();
        if (contextMap == null) {
            contextMap = Maps.newHashMap();
        }
        return contextMap.get(key);
    }

    /**
     * <p>设置值</p>
     *
     * @param key   键
     * @param value 值
     */
    private static void setValue(String key, Object value) {
        Map<String, Object> contextMap = context.get();
        if (contextMap == null) {
            contextMap = Maps.newHashMap();
        }
        contextMap.put(key, value);
        context.set(contextMap);
    }
}
