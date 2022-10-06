package com.sakuraio.nk.json.jackson.customizer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sakuraio.nk.core.constants.TimeConstants;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>HttpJackson2ObjectMapperBuilderCustomizer</p>
 *
 * @author nekoimi 2022/10/03
 */
public class CustomJackson2ObjectMapperBuilderCustomizer
        implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.serializerByType(LocalDate.class, new LocalDateSerializer(TimeConstants.DATE_FORMATTER));
        builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(TimeConstants.DATE_FORMATTER));
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(TimeConstants.DATE_TIME_FORMATTER));
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(TimeConstants.DATE_TIME_FORMATTER));
    }
}
