package com.sakuraio.nk.auth.security.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * <p>IgnoreAutoConfiguration</p>
 *
 * @author nekoimi 2022/10/13
 */
@EnableAutoConfiguration(
        exclude = SecurityAutoConfiguration.class
)
public class IgnoreAutoConfiguration {
}
