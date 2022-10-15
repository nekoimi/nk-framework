package com.sakuraio.nk.auth.api.exception;

import com.sakuraio.nk.error.Errors;
import com.sakuraio.nk.error.exception.BaseRuntimeException;

/**
 * <p>TokenInvalidException</p>
 *
 * @author nekoimi 2022/10/15
 */
public class TokenInvalidException extends BaseRuntimeException {

    public TokenInvalidException() {
        super(Errors.TOKEN_CANNOT_BE_PARSE_EXCEPTION);
    }
}
