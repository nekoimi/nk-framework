package com.sakuraio.nk.core.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sakuraio.nk.core.contract.ErrorDetails;
import com.sakuraio.nk.core.error.Errors;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>BaseResponse</p>
 *
 * @author nekoimi 2022/10/03
 */
@Slf4j
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {
    private static final Map<String, String> EMPTY_DATA = new HashMap<>();

    private Integer code;
    private String message;
    private String trace;
    private T data;

    public Boolean isOk() {
        return Objects.equals(Errors.OK.code(), this.getCode());
    }

    public static <T> BaseResponse<T> ok() {
        return ok(null);
    }

    public static <T> BaseResponse<T> ok(T data) {
        return BaseResponse.<T>builder()
                .code(Errors.OK.code())
                .message(Errors.OK.message())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> error(ErrorDetails errorDetails) {
        return BaseResponse.<T>builder()
                .code(errorDetails.code())
                .message(errorDetails.message())
                .trace(errorDetails.trace())
                .build();
    }
}
