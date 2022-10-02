package com.sakuraio.nk.feign.interceptor;

import com.sakuraio.nk.feign.TraceIdConstants;
import com.sakuraio.nk.feign.TraceIdGenerator;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * <p>TraceIdRequestInterceptor</p>
 *
 * @author nekoimi 2022/10/02
 */
public class TraceIdRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Map<String, Collection<String>> headers = template.headers();
        Collection<String> collection = headers.get(TraceIdConstants.HEADER_REQUEST_TRACE_ID);
        if (CollectionUtils.isEmpty(collection)) {
            template.header(TraceIdConstants.HEADER_REQUEST_TRACE_ID, TraceIdGenerator.get());
        }
    }
}
