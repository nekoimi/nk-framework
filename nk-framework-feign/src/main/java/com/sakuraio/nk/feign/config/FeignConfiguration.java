package com.sakuraio.nk.feign.config;

import com.sakuraio.nk.feign.interceptor.TraceIdRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>FeignConfiguration</p>
 *
 * @author nekoimi 2022/10/02
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public TraceIdRequestInterceptor traceIdRequestInterceptor() {
        return new TraceIdRequestInterceptor();
    }
}
