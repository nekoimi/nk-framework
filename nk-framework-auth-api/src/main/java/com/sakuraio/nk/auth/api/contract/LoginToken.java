package com.sakuraio.nk.auth.api.contract;

import cn.hutool.core.lang.Dict;

/**
 * <p>登录认证Token</p>
 *
 * @author nekoimi 2022/10/14
 */
public interface LoginToken {

    /**
     * <p>获取登录认证类型</p>
     *
     * @return
     */
    String getAuthType();

    /**
     * <p>获取登录参数</p>
     *
     * @return dict(map)：合并query string和post body
     */
    Dict getLoginParam();
}
