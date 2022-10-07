package com.sakuraio.nk.feign.config;

import com.sakuraio.nk.feign.FeignProperties;
import com.sakuraio.nk.feign.interceptor.TraceIdRequestInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * <p>FeignConfiguration</p>
 *
 * @author nekoimi 2022/10/02
 */
@EnableFeignClients
@EnableConfigurationProperties(
        FeignProperties.class
)
public class FeignConfiguration {

    @Bean
    public TraceIdRequestInterceptor traceIdRequestInterceptor() {
        return new TraceIdRequestInterceptor();
    }
}
