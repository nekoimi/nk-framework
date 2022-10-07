package com.sakuraio.nk.core.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p>SpringContextHolder</p>
 * <p>
 * nekoimi  2021/12/14 10:53
 */
@Slf4j
@Configuration
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext instance = null;

    public static ApplicationContext instance() {
        return instance;
    }

    public static Environment environment() {
        return instance().getEnvironment();
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return instance().getBean(requiredType);
    }

    public static void setInstance(ApplicationContext instance) {
        SpringContextHolder.instance = instance;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        log.info("SpringContextHolder save context!");
        SpringContextHolder.setInstance(context);
    }
}
