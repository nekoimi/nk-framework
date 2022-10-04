package com.sakuraio.nk.web.holder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>ObjectMapperHolder</p>
 *
 * nekoimi  2021/12/14 10:53
 */
@Component
public class ObjectMapperHolder implements ApplicationContextAware {
    private static ObjectMapper instance = new ObjectMapper();

    public static ObjectMapper instance() {
        return instance;
    }

    public static void setInstance(ObjectMapper instance) {
        ObjectMapperHolder.instance = instance;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ObjectMapperHolder.setInstance(context.getBean(ObjectMapper.class));
    }
}
