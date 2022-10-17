package com.sakuraio.nk.core.utils;

import org.apache.commons.lang3.StringUtils;

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
        if (throwable == null) {
            return StringUtils.EMPTY;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
