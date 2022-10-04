package com.sakuraio.nk.feign;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>FeignProperties</p>
 *
 * @author nekoimi 2022/10/02
 */
@Component
@ConfigurationProperties(prefix = "nk.feign")
public class FeignProperties {
    /**
     * <p>服务地址</p>
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
