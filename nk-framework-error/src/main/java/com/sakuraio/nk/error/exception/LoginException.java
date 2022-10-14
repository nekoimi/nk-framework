package com.sakuraio.nk.error.exception;

import com.sakuraio.nk.core.contract.ErrorDetails;

/**
 * <p>LoginException</p>
 * <p>
 * 登录认证异常
 *
 * @author nekoimi 2022/10/14
 */
public class LoginException extends BaseRuntimeException {

    public LoginException(Integer code, String message) {
        super(code, message);
    }

    public LoginException(ErrorDetails errorDetails) {
        super(errorDetails);
    }
}
