package com.sakuraio.nk.json.fastjson.config;

import com.sakuraio.nk.json.api.JsonOperations;
import com.sakuraio.nk.json.fastjson.FastjsonJsonOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
