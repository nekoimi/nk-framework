package com.sakuraio.nk.web.filter;

import com.sakuraio.nk.util.http.RequestUtils;
import com.sakuraio.nk.web.wrapper.HttpRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>BeforeRequestFilter</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
public class BeforeRequestFilter extends OncePerRequestFilter implements Ordered {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Filter: BeforeRequestFilter");

        if (request.getRequestURI().contains("favicon.ico")) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            response.getOutputStream().print(HttpStatus.NO_CONTENT.getReasonPhrase());
            return;
        }

        // 如果是文件上传请求，直接执行后续逻辑
        if (RequestUtils.isRequestMultipartFormData(request)) {
            // next original
            filterChain.doFilter(request, response);
            return;
        }

        // next wrapper
        // 非文件上传表单，包装请求之后再执行后续逻辑
        filterChain.doFilter(new HttpRequestWrapper(request), response);
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
