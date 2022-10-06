package com.sakuraio.nk.web.utils;

import com.sakuraio.nk.web.holder.SpringContextHolder;

/**
 * <p>SpringPropertyUtils</p>
 *
 * @author nekoimi 2022/10/06
 */
public class SpringPropertyUtils {
    private static final String PROP_APPLICATION_NAME = "spring.application.name";
    private static final String PROP_SERVER_HOST = "server.address";
    private static final String PROP_SERVER_PORT = "server.port";

    private SpringPropertyUtils() {
    }

    /**
     * <p>获取 application.name 属性配置</p>
     *
     * @return
     */
    public static String applicationName() {
        return SpringContextHolder.environment().getProperty(PROP_APPLICATION_NAME);
    }

    /**
     * <p>获取服务地址</p>
     *
     * @return
     */
    public static String serverHost() {
        return SpringContextHolder.environment().getProperty(PROP_SERVER_HOST);
    }

    /**
     * <p>获取服务运行端口配置</p>
     *
     * @return
     */
    public static String serverPort() {
        return SpringContextHolder.environment().getProperty(PROP_SERVER_PORT);
    }

    /**
     * <p>获取属性配置值</p>
     *
     * @param propName 名称
     * @return
     */
    public static String getProperty(String propName) {
        return SpringContextHolder.environment().getProperty(propName);
    }

    /**
     * <p>获取属性配置值</p>
     *
     * @param propName     名称
     * @param defaultValue 默认值
     * @return
     */
    public static String getProperty(String propName, String defaultValue) {
        return SpringContextHolder.environment().getProperty(propName, defaultValue);
    }
}
