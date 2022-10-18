package com.sakuraio.nk.core.contract;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p>ErrorDetails</p>
 *
 * @author nekoimi 2022/10/03
 */
public interface ErrorDetails extends Serializable {
    String PROP_CODE = "code";
    String PROP_HTTP_METHOD = "method";
    String PROP_HTTP_PATH = "path";
    String PROP_MESSAGE = "message";
    String PROP_TRACE = "trace";

    Integer code();

    String message();

    default String method() {
        return StringUtils.EMPTY;
    }

    default String path() {
        return StringUtils.EMPTY;
    }

    default String trace() {
        return StringUtils.EMPTY;
    }
}
