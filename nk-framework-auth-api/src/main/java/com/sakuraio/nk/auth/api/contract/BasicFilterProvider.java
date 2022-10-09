package com.sakuraio.nk.auth.api.contract;

import javax.servlet.Filter;

/**
 * <p>BasicFilterProvider</p>
 * <p>
 * 基础扩展Filter提供接口
 * 下面接口中的Filter，接入项目中的形式会遇到几种情况
 * 1、不接入安全框架(shiro、spring-security)，直接定义Filter应用到项目中，不需要加入到安全过滤链中
 * 2、接入安全框架(shiro、spring-security)，需要跟安全框架一起加入安全过滤链中
 * 所以这里使用提供者的方式，来根据情况来选择不同的Filter加载方式
 *
 * @author nekoimi 2022/10/09
 */
public interface BasicFilterProvider {

    /**
     * <p>预处理请求Filter</p>
     *
     * @return
     */
    Filter beforeRequestFilter();

    /**
     * <p>调试日志请求Filter</p>
     *
     * @return
     */
    Filter debugLogRequestFilter();

    /**
     * <p>TraceId处理Filter</p>
     *
     * @return
     */
    Filter traceRequestFilter();
}
