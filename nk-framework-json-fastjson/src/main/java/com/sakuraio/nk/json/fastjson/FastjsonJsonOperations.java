package com.sakuraio.nk.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.sakuraio.nk.json.api.JsonOperations;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>FastjsonJsonUtils</p>
 *
 * @author nekoimi 2022/10/07
 */
@Slf4j
public class FastjsonJsonOperations implements JsonOperations {

    @Override
    public String writeValueAsString(Object objectValue) {
        return JSON.toJSONString(objectValue);
    }

    @Override
    public byte[] writeValueAsBytes(Object objectValue) {
        return JSON.toJSONBytes(objectValue);
    }

    @Override
    public <T> T readValue(String json, Class<T> resultType) {
        return JSON.parseObject(json, resultType);
    }

    @Override
    public <T> T readValue(byte[] json, Class<T> resultType) {
        return JSON.parseObject(json, resultType);
    }
}
