package com.sakuraio.nk.web.utils;

import com.google.common.collect.Maps;
import com.sakuraio.nk.core.constants.Headers;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>RequestUtils</p>
 *
 * @author nekoimi 2022/10/04
 */
public class RequestUtils {

    private RequestUtils() {
    }

    /**
     * <p>获取 Token</p>
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(Headers.AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            return null;
        }

        if (StringUtils.startsWith(token, "Bearer ")) {
            token = token.substring(7);
        }

        return token;
    }

    /**
     * <p>Query String 转 Map</p>
     *
     * @param queryString
     * @return
     */
    public static Map<String, String> queryAsMap(String queryString) {
        Map<String, String> queryMap = Maps.newHashMap();
        if (StringUtils.isBlank(queryString)) {
            return queryMap;
        }
        if (queryString.startsWith("?")) {
            queryString = queryString.substring(1);
        }
        String[] kvList = queryString.split("&");
        for (String kv : kvList) {
            if (StringUtils.isNotBlank(kv)) {
                String[] kAndV = kv.split("=");
                if (kAndV.length >= 2) {
                    queryMap.put(kAndV[0], URLDecoder.decode(kAndV[1], StandardCharsets.UTF_8));
                } else {
                    queryMap.put(kAndV[0], null);
                }
            }
        }
        return queryMap;
    }
}
