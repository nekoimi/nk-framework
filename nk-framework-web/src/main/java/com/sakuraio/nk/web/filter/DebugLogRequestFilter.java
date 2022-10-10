package com.sakuraio.nk.web.filter;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>DebugLogRequestFilter</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
public class DebugLogRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Filter: DebugLogRequestFilter");
        // 请求处理前日志
        StringBuilder requestLog = new StringBuilder(100);
        // 日志参数
        List<Object> debugLogArgs = Lists.newArrayList();
        requestLog.append("\n\n==================== Request Start ====================\n");
        // 请求方法
        // 请求Url
        requestLog.append("===> {}: {}\n");
        debugLogArgs.add(request.getMethod());
        StringBuilder requestUrlBuilder = new StringBuilder(request.getRequestURI());
        String requestQuery = request.getQueryString();
        if (StringUtils.isNotBlank(requestQuery)) {
            requestUrlBuilder.append("?");
            requestUrlBuilder.append(URLDecoder.decode(requestQuery, StandardCharsets.UTF_8));
        }
        String requestUrl = requestUrlBuilder.toString();
        debugLogArgs.add(requestUrl);
        // 请求Header
        Enumeration<String> headerNames = request.getHeaderNames();
        headerNames.asIterator().forEachRemaining(headerName -> {
            requestLog.append("> Header {}: {}\n");
            requestLog.append(headerName);
            requestLog.append(StringUtils.join(request.getHeaders(headerName), ","));
        });

        // 输出请求日志
        log.debug(requestLog.toString(), debugLogArgs.toArray());

        // 执行业务处理逻辑
        filterChain.doFilter(request, response);

        // 请求处理后日志

    }
}
