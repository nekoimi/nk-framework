package com.sakuraio.nk.auth.api.contract;

import cn.hutool.core.lang.Dict;
import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;

/**
 * <p>LoginServiceProvider</p>
 *
 * @author nekoimi 2022/10/14
 */
public interface LoginServiceProvider {

    /**
     * <p>认证类型</p>
     *
     * @return
     */
    String getAuthType();

    /**
     * <p>是否支持 {@param authType} 指定的认证类型</p>
     *
     * @param authType
     * @return
     */
    boolean supports(String authType);

    /**
     * <p>执行登录</p>
     *
     * @param loginParam 请求参数(合并queryString和postBody)
     * @return
     */
    JwtSubject doLogin(Dict loginParam);
}
