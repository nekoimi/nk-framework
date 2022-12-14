package com.sakuraio.nk.auth.api.exception;

import com.sakuraio.nk.core.error.Errors;
import com.sakuraio.nk.core.error.exception.BaseRuntimeException;

/**
 * <p>TokenExpireException</p>
 *
 * @author nekoimi 2022/10/15
 */
public class TokenExpireException extends BaseRuntimeException {

    public TokenExpireException() {
        super(Errors.TOKEN_EXPIRED_EXCEPTION);
    }
}
