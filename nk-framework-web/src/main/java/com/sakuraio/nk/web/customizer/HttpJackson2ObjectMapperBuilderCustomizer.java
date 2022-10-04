package com.sakuraio.nk.web.customizer;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * <p>Jackson2ObjectMapperBuilder</p>
 *
 * @author nekoimi 2022/10/03
 */
public class HttpJackson2ObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {

    }
}
