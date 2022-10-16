package com.sakuraio.nk.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.sakuraio.nk.core.holder.SpringContextHolder;

/**
 * <p>JsonUtils</p>
 *
 * @author nekoimi 2022/10/07
 */
public class JsonUtils {

    private JsonUtils() {
    }

    public static String writeValueAsString(Object objectValue) {
        return SpringContextHolder.getBean(JsonOperations.class).writeValueAsString(objectValue);
    }

    public static byte[] writeValueAsBytes(Object objectValue) {
        return SpringContextHolder.getBean(JsonOperations.class).writeValueAsBytes(objectValue);
    }

    public static  <T> T readValue(String json, Class<T> resultType) {
        return SpringContextHolder.getBean(JsonOperations.class).readValue(json, resultType);
    }

    public static  <T> T readValue(String json, TypeReference<T> resultType) {
        return SpringContextHolder.getBean(JsonOperations.class).readValue(json, resultType);
    }

    public static  <T> T readValue(String json, JavaType resultType) {
        return SpringContextHolder.getBean(JsonOperations.class).readValue(json, resultType);
    }

    public static  <T> T readValue(byte[] json, Class<T> resultType) {
        return SpringContextHolder.getBean(JsonOperations.class).readValue(json, resultType);
    }

    public static  <T> T readValue(byte[] json, TypeReference<T> resultType) {
        return SpringContextHolder.getBean(JsonOperations.class).readValue(json, resultType);
    }

    public static  <T> T readValue(byte[] json, JavaType resultType) {
        return SpringContextHolder.getBean(JsonOperations.class).readValue(json, resultType);
    }
}
