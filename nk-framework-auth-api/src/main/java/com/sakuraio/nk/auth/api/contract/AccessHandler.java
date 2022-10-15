package com.sakuraio.nk.auth.api.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>AccessHandler</p>
 * <p>
 * 授权检测处理器
 *
 * @author nekoimi 2022/10/15
 */
public interface AccessHandler {

    /**
     * <p>授权检测</p>
     *
     * @param request  请求对象
     * @param response 响应对象
     */
    void handleAccess(HttpServletRequest request, HttpServletResponse response);
}
