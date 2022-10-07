package com.sakuraio.nk.web.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>CorsProperties</p>
 *
 * @author nekoimi 2022/10/04
 */
@Data
@ConfigurationProperties(prefix = "nk.web.cors")
public class CorsProperties {
    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;
    private Boolean allowCredentials;
    private Long maxAge;
}
