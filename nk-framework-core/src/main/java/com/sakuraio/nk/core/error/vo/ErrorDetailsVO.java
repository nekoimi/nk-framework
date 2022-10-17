package com.sakuraio.nk.core.error.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sakuraio.nk.core.contract.ErrorDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>ErrorDetailsVO</p>
 *
 * @author nekoimi 2022/10/04
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetailsVO implements ErrorDetails {
    private final Integer code;
    private final String message;
    private final String trace;

    public ErrorDetailsVO(Integer code, String message, String trace) {
        this.code = code;
        this.message = message;
        this.trace = trace;
    }

    public static ErrorDetailsVO of(Integer code, String message, String trace) {
        return new ErrorDetailsVO(code, message, trace);
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String trace() {
        return trace;
    }
}
