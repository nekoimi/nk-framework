package com.sakuraio.nk.core.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * <p>SpringContextHolder</p>
 * <p>
 * nekoimi  2021/12/14 10:53
 */
@Component
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
        SpringContextHolder.setInstance(context);
    }
}
