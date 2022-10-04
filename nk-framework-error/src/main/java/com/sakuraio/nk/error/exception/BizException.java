package com.sakuraio.nk.error.exception;

import com.sakuraio.nk.core.contract.ErrorDetails;

/**
 * <p>BizException</p>
 *
 * @author nekoimi 2022/10/04
 */
public class BizException extends RuntimeException {
    private final Integer code;
    private final String trace;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.trace = null;
    }

    public BizException(Integer code, String message, String trace) {
        super(message);
        this.code = code;
        this.trace = trace;
    }

    public BizException(ErrorDetails errorDetails) {
        super(errorDetails.message());
        this.code = errorDetails.code();
        this.trace = errorDetails.trace();
    }

    public Integer getCode() {
        return this.code;
    }

    public String getTrace() {
        return this.trace;
    }
}
