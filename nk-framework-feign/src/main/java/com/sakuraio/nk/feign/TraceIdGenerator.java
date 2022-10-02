package com.sakuraio.nk.feign;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * <p>TraceIdGenerator</p>
 *
 * @author nekoimi 2022/10/02
 */
public class TraceIdGenerator {

    private TraceIdGenerator() {
    }

    /**
     * <p>获取当前线程上下文TraceId</p>
     *
     * @return traceId
     */
    public static String get() {
        String traceId = MDC.get(TraceIdConstants.HEADER_REQUEST_TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = generate();
            MDC.put(TraceIdConstants.HEADER_REQUEST_TRACE_ID, traceId);
        }
        return traceId;
    }

    /**
     * <p>生成TraceId</p>
     *
     * @return traceId
     */
    public static String generate() {
        return "nk-trace-" + UUID.randomUUID().toString().replace("-", "");
    }
}
