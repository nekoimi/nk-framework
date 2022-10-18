package com.sakuraio.nk.core.error.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sakuraio.nk.core.contract.ErrorDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;

/**
 * <p>ErrorDetailsVO</p>
 *
 * @author nekoimi 2022/10/04
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetailsVO extends LinkedHashMap<String, Object> implements ErrorDetails {

    private ErrorDetailsVO(Integer code, String method, String path, String message, String trace) {
        super(5);
        this.put(PROP_CODE, code);
        this.put(PROP_HTTP_METHOD, method);
        this.put(PROP_HTTP_PATH, path);
        this.put(PROP_MESSAGE, message);
        this.put(PROP_TRACE, trace);
    }

    public static ErrorDetailsVO of(Integer code, String method, String path, String message, String trace) {
        return new ErrorDetailsVO(code, method, path, message, trace);
    }

    @Override
    public Integer code() {
        return (Integer) this.get(PROP_CODE);
    }

    @Override
    public String message() {
        return (String) this.get(PROP_MESSAGE);
    }

    @Override
    public String method() {
        return (String) this.get(PROP_HTTP_METHOD);
    }

    @Override
    public String path() {
        return (String) this.get(PROP_HTTP_PATH);
    }

    @Override
    public String trace() {
        return (String) this.get(PROP_TRACE);
    }
}
