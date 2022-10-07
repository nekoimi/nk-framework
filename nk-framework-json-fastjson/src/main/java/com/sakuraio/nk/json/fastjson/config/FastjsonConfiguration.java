package com.sakuraio.nk.json.fastjson.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.sakuraio.nk.core.constants.TimeConstants;
import com.sakuraio.nk.json.api.JsonOperations;
import com.sakuraio.nk.json.fastjson.FastjsonJsonOperations;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * <p>FastjsonConfiguration</p>
 *
 * @author nekoimi 2022/10/07
 */
@Configuration
public class FastjsonConfiguration {

    @Bean
    public JsonOperations jsonOperations() {
        return new FastjsonJsonOperations();
    }

    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(StandardCharsets.UTF_8);
        config.setDateFormat(TimeConstants.DATE_TIME_FORMAT);
        config.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.SkipTransientField,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteBigDecimalAsPlain,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.DisableCircularReferenceDetect
        );
        return config;
    }

    @Bean
    public HttpMessageConverters httpMessageConverters(FastJsonConfig fastJsonConfig) {
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        httpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(httpMessageConverter);
    }
}
