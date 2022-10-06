package com.sakuraio.nk.web.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.sakuraio.nk.web.holder.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>JsonUtils</p>
 *
 * nekoimi  2021/12/14 10:55
 */
@Slf4j
public class JsonUtils {

    private JsonUtils() {
    }

    public static String write(Object src) {
        if (src instanceof CharSequence) {
            return src.toString();
        }
        try {
            return ObjectMapperHolder.instance().writeValueAsString(src);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static byte[] writeBytes(Object src) {
        if (src instanceof CharSequence) {
            return src.toString().getBytes(StandardCharsets.UTF_8);
        }
        try {
            return ObjectMapperHolder.instance().writeValueAsBytes(src);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return new byte[0];
        }
    }

    public static <T> T read(String json, Class<T> resultType) {
        try {
            return ObjectMapperHolder.instance().readValue(json, resultType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> T read(String json, TypeReference<T> resultType) {
        try {
            return ObjectMapperHolder.instance().readValue(json, resultType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> T read(String json, JavaType javaType) {
        try {
            return ObjectMapperHolder.instance().readValue(json, javaType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> T readBytes(byte[] json, Class<T> resultType) {
        try {
            return ObjectMapperHolder.instance().readValue(json, resultType);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}