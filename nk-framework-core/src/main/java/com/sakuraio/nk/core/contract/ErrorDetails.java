package com.sakuraio.nk.core.contract;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>ErrorDetails</p>
 *
 * @author nekoimi 2022/10/03
 */
public interface ErrorDetails {
    Integer code();
    String message();
    default String trace() {
        return StringUtils.EMPTY;
    }
}
