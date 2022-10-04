package com.sakuraio.nk.core.contract;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p>ErrorDetails</p>
 *
 * @author nekoimi 2022/10/03
 */
public interface ErrorDetails extends Serializable {
    Integer code();
    String message();
    default String trace() {
        return StringUtils.EMPTY;
    }
}
