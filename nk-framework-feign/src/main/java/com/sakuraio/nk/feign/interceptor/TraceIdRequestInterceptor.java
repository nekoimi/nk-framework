package com.sakuraio.nk.feign.interceptor;

import com.sakuraio.nk.constants.RequestConstants;
import com.sakuraio.nk.core.trace.TraceIdGenerator;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * <p>TraceIdRequestInterceptor</p>
 *
 * @author nekoimi 2022/10/02
 */
@Slf4j
public class TraceIdRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Map<String, Collection<String>> headers = template.headers();
        Collection<String> collection = headers.get(RequestConstants.HEADER_TRACE_ID);
        if (CollectionUtils.isEmpty(collection)) {
            template.header(RequestConstants.HEADER_TRACE_ID, TraceIdGenerator.get());
            log.debug("feign traceId append: {}", TraceIdGenerator.get());
        }
    }
}
