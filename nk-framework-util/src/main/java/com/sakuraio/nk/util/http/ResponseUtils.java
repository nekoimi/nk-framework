package com.sakuraio.nk.util.http;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>ResponseUtils</p>
 *
 * @author nekoimi 2022/10/15
 */
public class ResponseUtils {

    private ResponseUtils() {
    }

    /**
     * <p>发送json响应</p>
     *
     * @param response     httpResponse对象
     * @param responseJson 结果对象json
     */
    public static void sendJson(HttpServletResponse response, String responseJson) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        try {
            response.getOutputStream().print(responseJson);
            response.getOutputStream().flush();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
