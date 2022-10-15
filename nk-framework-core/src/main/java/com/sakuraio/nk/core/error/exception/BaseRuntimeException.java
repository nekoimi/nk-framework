package com.sakuraio.nk.core.error.exception;

import com.sakuraio.nk.core.contract.ErrorDetails;
import com.sakuraio.nk.core.error.Errors;

/**
 * <p>BaseRuntimeException</p>
 *
 * @author nekoimi 2022/10/14
 */
public abstract class BaseRuntimeException extends RuntimeException {
    private final Integer code;
    private final String trace;

    protected BaseRuntimeException() {
        super(Errors.SERVER_ERROR.message());
        this.code = Errors.SERVER_ERROR.code();
        this.trace = null;
    }

    protected BaseRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
        this.trace = null;
    }

    protected BaseRuntimeException(Integer code, String message, String trace) {
        super(message);
        this.code = code;
        this.trace = trace;
    }

    protected BaseRuntimeException(ErrorDetails errorDetails) {
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
