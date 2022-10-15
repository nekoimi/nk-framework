package com.sakuraio.nk.auth.api;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>UrlAllow</p>
 * <p>
 * 可以直接访问的路径白名单
 *
 * @author nekoimi 2022/10/15
 */
public final class UrlAllow {
    private static final List<String> urlAllowList = Lists.newArrayList();

    private UrlAllow() {
    }

    public static List<String> getUrlAllowList() {
        return urlAllowList;
    }

    static {
        urlAllowList.add("/");
        urlAllowList.add("/favicon.ico");
        urlAllowList.add("/doc.html");
        urlAllowList.add("/v2/api-docs");
        urlAllowList.add("/swagger-resources");
        urlAllowList.add("/swagger-resources/**");
    }
}
