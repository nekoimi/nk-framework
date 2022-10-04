package com.yoyohr.boot.framework.holder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * nekoimi  2021/12/14 10:53
 */
@Component
public class ObjectMapperHolder implements ApplicationContextAware {
    private static ObjectMapper INSTANCE = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }

    public static void setInstance(ObjectMapper INSTANCE) {
        ObjectMapperHolder.INSTANCE = INSTANCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ObjectMapperHolder.setInstance(context.getBean(ObjectMapper.class));
    }
}
