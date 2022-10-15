package com.sakuraio.nk.core.error.exception;

import com.sakuraio.nk.core.error.Errors;

/**
 * <p>BaseResponseException</p>
 *
 * @author nekoimi 2022/10/15
 */
public class BaseResponseException extends BaseRuntimeException {

    public BaseResponseException(String message) {
        super(Errors.SERVER_ERROR.code(), message);
    }

    public BaseResponseException(Integer code, String message) {
        super(code, message);
    }

    public BaseResponseException(Integer code, String message, String trace) {
        super(code, message, trace);
    }
}
