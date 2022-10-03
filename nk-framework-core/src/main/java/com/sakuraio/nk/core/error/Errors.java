package com.sakuraio.nk.core.error;

import com.sakuraio.nk.core.contract.ErrorDetails;

/**
 * <p>Errors</p>
 *
 * @author nekoimi 2022/10/03
 */
public enum Errors implements ErrorDetails {
    OK(0, "ok");

    private final Integer code;
    private final String message;

    Errors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
