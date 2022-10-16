package com.sakuraio.nk.json.fastjson;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>FastjsonJsonUtils</p>
 *
 * @author nekoimi 2022/10/07
 */
@Slf4j
public class FastjsonJsonOperations {

    public String writeValueAsString(Object objectValue) {
        return JSON.toJSONString(objectValue);
    }

    public byte[] writeValueAsBytes(Object objectValue) {
        return JSON.toJSONBytes(objectValue);
    }

    public <T> T readValue(String json, Class<T> resultType) {
        return JSON.parseObject(json, resultType);
    }

    public <T> T readValue(byte[] json, Class<T> resultType) {
        return JSON.parseObject(json, resultType);
    }
}
