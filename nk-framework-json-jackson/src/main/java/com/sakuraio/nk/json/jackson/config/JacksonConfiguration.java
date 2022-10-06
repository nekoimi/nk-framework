package com.sakuraio.nk.json.jackson.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sakuraio.nk.core.constants.TimeConstants;
import com.sakuraio.nk.json.api.JsonOperations;
import com.sakuraio.nk.json.jackson.JacksonJsonOperations;
import com.sakuraio.nk.json.jackson.customizer.CustomJackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Locale;
import java.util.TimeZone;

/**
 * <p>JacksonConfiguration</p>
 *
 * nekoimi  2021/12/16 18:53
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public JsonOperations jsonOperations() {
        return new JacksonJsonOperations();
    }

    @Bean
    @Primary
    public JacksonProperties jacksonProperties(JacksonProperties properties) {
        properties.setLocale(Locale.SIMPLIFIED_CHINESE);
        properties.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        properties.getVisibility().put(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        properties.setDateFormat(TimeConstants.DATE_FORMAT);
        properties.setTimeZone(TimeZone.getDefault());
        properties.getDeserialization().put(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        properties.getDeserialization().put(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        properties.getSerialization().put(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        properties.getSerialization().put(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        properties.getSerialization().put(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
        properties.getSerialization().put(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        properties.getSerialization().put(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        return properties;
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
