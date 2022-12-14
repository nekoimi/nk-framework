package com.sakuraio.nk.web.filter;

import com.sakuraio.nk.constants.RequestConstants;
import com.sakuraio.nk.util.http.RequestUtils;
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
 * <p>TraceRequestFilter</p>
 *
 * @author nekoimi 2022/10/06
 */
@Slf4j
public class TraceRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Filter: TraceRequestFilter");
        try {
            String traceId = RequestUtils.getTraceId(request);
            if (StringUtils.isNotBlank(traceId)) {
                MDC.put(RequestConstants.HEADER_TRACE_ID, traceId);
            }
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(RequestConstants.HEADER_TRACE_ID);
        }
    }
}
