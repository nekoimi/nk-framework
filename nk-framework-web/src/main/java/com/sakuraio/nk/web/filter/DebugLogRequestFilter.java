package com.sakuraio.nk.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        filterChain.doFilter(request, response);
    }
}
