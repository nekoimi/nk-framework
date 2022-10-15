package com.sakuraio.nk.auth.api.exception;

import com.sakuraio.nk.error.Errors;
import com.sakuraio.nk.error.exception.BaseRuntimeException;

/**
 * <p>TokenCannotBeRefreshException</p>
 *
 * @author nekoimi 2022/10/15
 */
public class TokenCannotBeRefreshException extends BaseRuntimeException {

    public TokenCannotBeRefreshException() {
        super(Errors.TOKEN_CANNOT_BE_REFRESH_EXCEPTION);
    }
}
