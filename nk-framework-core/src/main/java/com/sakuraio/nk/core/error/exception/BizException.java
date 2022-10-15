package com.sakuraio.nk.core.error.exception;

import com.sakuraio.nk.core.contract.ErrorDetails;

/**
 * <p>BizException</p>
 * <p>
 * 业务异常
 *
 * @author nekoimi 2022/10/04
 */
public class BizException extends BaseRuntimeException {

    public BizException(Integer code, String message) {
        super(code, message);
    }

    public BizException(Integer code, String message, String trace) {
        super(code, message, trace);
    }

    public BizException(ErrorDetails errorDetails) {
        super(errorDetails);
    }
}
