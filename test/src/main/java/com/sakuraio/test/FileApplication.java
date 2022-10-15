package com.sakuraio.test;

import com.sakuraio.nk.auth.security.EnableSecurityAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <p>FileApplication</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
@EnableWebMvc
@EnableDiscoveryClient
@EnableSecurityAccess
@SpringBootApplication
public class FileApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FileApplication.class).bannerMode(Banner.Mode.OFF).run(args);
    }
}
