package com.sakuraio.nk.swagger2.config.properties;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>SwaggerProperties</p>
 *
 * @author nekoimi 2022/10/07
 */
@Data
@ConfigurationProperties(prefix = "nk.swagger")
public class SwaggerProperties {
    /**
     * <p>Swagger调试不需要验证Token放行路径列表</p>
     */
    private List<String> permitAll = Lists.newArrayList();
}
