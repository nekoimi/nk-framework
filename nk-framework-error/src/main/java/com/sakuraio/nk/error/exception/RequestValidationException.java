package com.sakuraio.nk.error.exception;

import com.sakuraio.nk.error.Errors;

/**
 * <p>RequestValidationException</p>
 *
 * @author nekoimi 2022/10/15
 */
public class RequestValidationException extends BaseRuntimeException {

    public RequestValidationException() {
        super(Errors.REQUEST_VALIDATE_EXCEPTION);
    }

    public RequestValidationException(String message) {
        super(Errors.REQUEST_VALIDATE_EXCEPTION.code(), message);
    }
}
