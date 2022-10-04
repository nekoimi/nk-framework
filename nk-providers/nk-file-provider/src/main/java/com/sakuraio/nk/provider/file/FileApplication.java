package com.sakuraio.nk.provider.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * <p>FileApplication</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(value = "com.sakuraio.nk")
public class FileApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(FileApplication.class);
        ApplicationContext context = builder.bannerMode(Banner.Mode.OFF).run(args);
        String applicationName = context.getEnvironment().getProperty("spring.application.name");
        String serverPort = context.getEnvironment().getProperty("server.port");
        log.info("[{}] is running on http://127.0.0.1:{}", applicationName, serverPort);
    }
}
