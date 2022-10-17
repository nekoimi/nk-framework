package com.sakuraio.nk.web.config;

import com.sakuraio.nk.util.StepJump;
import com.sakuraio.nk.web.config.properties.CorsProperties;
import com.sakuraio.nk.web.error.WebErrorAttributes;
import com.sakuraio.nk.web.error.WebErrorController;
import com.sakuraio.nk.web.error.DefaultExceptionHandler;
import com.sakuraio.nk.web.filter.BeforeRequestFilter;
import com.sakuraio.nk.web.filter.DebugLogRequestFilter;
import com.sakuraio.nk.web.filter.TraceRequestFilter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.Filter;

/**
 * <p>WebCustomConfiguration</p>
 *
 * @author nekoimi 2022/10/04
 */
@EnableConfigurationProperties(CorsProperties.class)
@Import(DefaultExceptionHandler.class)
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class WebConfiguration {
    private static final StepJump FILTER_STEP_JUMP = StepJump.create(100, 10);
    private static final int BEFORE_FILTER_ORDER;
    private static final int DEBUG_LOG_FILTER_ORDER;
    private static final int TRACE_FILTER_ORDER;

    static {
        BEFORE_FILTER_ORDER = FILTER_STEP_JUMP.next();
        DEBUG_LOG_FILTER_ORDER = FILTER_STEP_JUMP.next();
        TRACE_FILTER_ORDER = FILTER_STEP_JUMP.next();
    }

    @Bean
    public CorsConfiguration corsConfiguration(CorsProperties corsProperties) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(corsProperties.getAllowedOrigins());
        configuration.setAllowedMethods(corsProperties.getAllowedMethods());
        configuration.setAllowedHeaders(corsProperties.getAllowedHeaders());
        configuration.setExposedHeaders(corsProperties.getExposedHeaders());
        configuration.setAllowCredentials(corsProperties.getAllowCredentials());
        configuration.setMaxAge(corsProperties.getMaxAge());
        return configuration;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsConfiguration configuration) {
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return configurationSource;
    }

    @Bean
    public FilterRegistrationBean<Filter> registerBeforeRequestFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BeforeRequestFilter());
        registrationBean.setOrder(BEFORE_FILTER_ORDER);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName(BeforeRequestFilter.class.getName());
        return registrationBean;
    }

    @Bean
    @ConditionalOnProperty(name = "debug", havingValue = "true")
    public FilterRegistrationBean<Filter> registerDebugLogRequestFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DebugLogRequestFilter());
        registrationBean.setOrder(DEBUG_LOG_FILTER_ORDER);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName(DebugLogRequestFilter.class.getName());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> registerTraceRequestFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceRequestFilter());
        registrationBean.setOrder(TRACE_FILTER_ORDER);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName(TraceRequestFilter.class.getName());
        return registrationBean;
    }

    @Bean
    public WebErrorController errorController(ErrorAttributes errorAttributes) {
        return new WebErrorController(errorAttributes);
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new WebErrorAttributes();
    }
}
