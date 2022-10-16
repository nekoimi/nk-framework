package com.sakuraio.nk.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>JacksonJsonUtils</p>
 *
 * @author nekoimi 2022/10/06
 */
@Slf4j
public class JacksonJsonOperations implements JsonOperations {

    private final ObjectMapper objectMapper;

    public JacksonJsonOperations(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String writeValueAsString(Object objectValue) {
        if (objectValue instanceof CharSequence) {
            return objectValue.toString();
        }
        try {
            return objectMapper.writeValueAsString(objectValue);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public byte[] writeValueAsBytes(Object objectValue) {
        if (objectValue instanceof CharSequence) {
            return objectValue.toString().getBytes(StandardCharsets.UTF_8);
        }
        try {
            return objectMapper.writeValueAsBytes(objectValue);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return new byte[0];
        }
    }

    @Override
    public <T> T readValue(String json, Class<T> resultType) {
        try {
            return objectMapper.readValue(json, resultType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T readValue(String json, TypeReference<T> resultType) {
        try {
            return objectMapper.readValue(json, resultType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T readValue(String json, JavaType resultType) {
        try {
            return objectMapper.readValue(json, resultType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T readValue(byte[] json, Class<T> resultType) {
        try {
            return objectMapper.readValue(json, resultType);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T readValue(byte[] json, TypeReference<T> resultType) {
        try {
            return objectMapper.readValue(json, resultType);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T readValue(byte[] json, JavaType resultType) {
        try {
            return objectMapper.readValue(json, resultType);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
