package com.sakuraio.nk.web.filter;

import com.sakuraio.nk.core.constants.Headers;
import com.sakuraio.nk.web.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>TraceProcessFilter</p>
 *
 * @author nekoimi 2022/10/06
 */
@Slf4j
public class TraceProcessFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceId = RequestUtils.getTraceId(request);
            if (StringUtils.isNotBlank(traceId)) {
                MDC.put(Headers.TRACE_ID, traceId);
            }
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(Headers.TRACE_ID);
        }
    }
}
