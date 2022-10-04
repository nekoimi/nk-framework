package com.sakuraio.nk.error.utils;

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
        PrintWriter writer = new PrintWriter(new StringWriter());
        throwable.printStackTrace(writer);
        return writer.toString();
    }
}
