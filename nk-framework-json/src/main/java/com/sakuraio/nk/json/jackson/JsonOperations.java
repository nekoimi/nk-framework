package com.sakuraio.nk.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

/**
 * <p>JsonOperations</p>
 *
 * @author nekoimi 2022/10/07
 */
public interface JsonOperations {

    /**
     * <p>转json字符串</p>
     *
     * @param objectValue 值
     * @return
     */
    String writeValueAsString(Object objectValue);

    /**
     * <p>转byte数组</p>
     *
     * @param objectValue 值
     * @return
     */
    byte[] writeValueAsBytes(Object objectValue);

    /**
     * <p>read of type</p>
     *
     * @param json       json字符串
     * @param resultType 反序列化类型
     * @param <T>
     * @return
     */
    <T> T readValue(String json, Class<T> resultType);

    /**
     * <p>read of type</p>
     *
     * @param json       json字符串
     * @param resultType 反序列化类型
     * @param <T>
     * @return
     */
    <T> T readValue(String json, TypeReference<T> resultType);

    /**
     * <p>read of type</p>
     *
     * @param json       json字符串
     * @param resultType 反序列化类型
     * @param <T>
     * @return
     */
    <T> T readValue(String json, JavaType resultType);

    /**
     * <p>read of type</p>
     *
     * @param json       json byte数组
     * @param resultType 反序列化类型
     * @param <T>
     * @return
     */
    <T> T readValue(byte[] json, Class<T> resultType);

    /**
     * <p>read of type</p>
     *
     * @param json       json byte数组
     * @param resultType 反序列化类型
     * @param <T>
     * @return
     */
    <T> T readValue(byte[] json, TypeReference<T> resultType);

    /**
     * <p>read of type</p>
     *
     * @param json       json byte数组
     * @param resultType 反序列化类型
     * @param <T>
     * @return
     */
    <T> T readValue(byte[] json, JavaType resultType);
}
