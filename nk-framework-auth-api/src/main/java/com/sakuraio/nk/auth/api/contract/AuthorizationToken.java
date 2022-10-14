package com.sakuraio.nk.auth.api.contract;

/**
 * <p>授权Token</p>
 *
 * @author nekoimi 2022/10/14
 */
public interface AuthorizationToken {

    /**
     * <p>token值</p>
     *
     * @return
     */
    String getToken();

    /**
     * <p>token验证对象</p>
     * <p>
     * 请求过来的token验证成功之后，获取token对应的对象信息
     *
     * @return
     */
    JwtSubject getSubject();
}
