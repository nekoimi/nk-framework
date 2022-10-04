package com.sakuraio.nk.web.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sakuraio.nk.web.config.properties.CorsProperties;
import com.sakuraio.nk.web.customizer.CustomJackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * <p>WebCustomConfiguration</p>
 *
 * @author nekoimi 2022/10/04
 */
@Configuration
public class WebCustomConfiguration {

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
    public SimpleModule longSafeModule() {
        // 追加自定义配置：Long 数据转 String 类型，避免 js 丢失精度
        SimpleModule longSafeModule = new SimpleModule();
        longSafeModule.addSerializer(Long.class, ToStringSerializer.instance);
        longSafeModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        return longSafeModule;
    }

    @Bean
    @ConditionalOnMissingBean(value = Jdk8Module.class)
    public Jdk8Module jdk8Module() {
        return new Jdk8Module();
    }

    @Bean
    @ConditionalOnMissingBean(value = JavaTimeModule.class)
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson2ObjectMapperBuilderCustomizer() {
        return new CustomJackson2ObjectMapperBuilderCustomizer();
    }
}
