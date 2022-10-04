package com.sakuraio.nk.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p>RequestDebugLogFilter</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
@Component
public class RequestDebugLogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        chain.doFilter(request, response);
    }
}
