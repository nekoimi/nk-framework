package com.sakuraio.nk.util.http;

import cn.hutool.core.lang.Dict;
import com.sakuraio.nk.constants.RequestConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>RequestUtils</p>
 *
 * @author nekoimi 2022/10/04
 */
public class RequestUtils {

    private RequestUtils() {
    }

    /**
     * <p>是否是文件上传请求</p>
     *
     * @param request
     * @return
     */
    public static boolean isRequestMultipartFormData(HttpServletRequest request) {
        return StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/form-data");
    }

    /**
     * <p>获取 Token</p>
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(RequestConstants.HEADER_AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            return null;
        }

        if (StringUtils.startsWith(token, "Bearer ")) {
            token = token.substring(7);
        }

        return token;
    }

    /**
     * <p>获取AuthType</p>
     *
     * @param request
     * @return
     */
    public static String getAuthType(HttpServletRequest request) {
        String authType = request.getHeader(RequestConstants.HEADER_AUTH_TYPE);
        if (StringUtils.isBlank(authType)) {
            authType = queryAsDict(request.getQueryString()).getStr(RequestConstants.QUERY_AUTH_TYPE);
        }
        return authType;
    }

    /**
     * <p>获取 TraceId</p>
     *
     * @param request
     * @return
     */
    public static String getTraceId(HttpServletRequest request) {
        return request.getHeader(RequestConstants.HEADER_TRACE_ID);
    }

    /**
     * <p>Query String 转 Map</p>
     *
     * @param queryString
     * @return
     */
    public static Dict queryAsDict(String queryString) {
        Dict queryDict = Dict.create();
        if (StringUtils.isBlank(queryString)) {
            return queryDict;
        }
        if (queryString.startsWith("?")) {
            queryString = queryString.substring(1);
        }
        String[] kvList = queryString.split("&");
        for (String kv : kvList) {
            if (StringUtils.isNotBlank(kv)) {
                String[] kAndV = kv.split("=");
                if (kAndV.length >= 2) {
                    queryDict.put(kAndV[0], URLDecoder.decode(kAndV[1], StandardCharsets.UTF_8));
                } else {
                    queryDict.put(kAndV[0], null);
                }
            }
        }
        return queryDict;
    }
}
