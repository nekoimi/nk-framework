package com.sakuraio.nk.auth.api.exception;

import com.sakuraio.nk.core.contract.ErrorDetails;
import com.sakuraio.nk.core.error.exception.BaseRuntimeException;

/**
 * <p>LoginFailedException</p>
 * <p>
 * 登录认证失败异常
 *
 * @author nekoimi 2022/10/14
 */
public class LoginFailedException extends BaseRuntimeException {

    public LoginFailedException(Integer code, String message) {
        super(code, message);
    }

    public LoginFailedException(ErrorDetails errorDetails) {
        super(errorDetails);
    }
}
