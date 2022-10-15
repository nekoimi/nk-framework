package com.sakuraio.nk.core.error.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>ErrorUtils</p>
 *
 * @author nekoimi 2022/10/04
 */
public class ErrorUtils {

    private ErrorUtils() {
    }

    /**
     * <p>获取异常Trace详细信息</p>
     *
     * @param throwable
     * @return
     */
    public static String getTraceString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
