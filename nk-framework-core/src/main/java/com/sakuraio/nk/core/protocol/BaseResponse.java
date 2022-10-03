package com.sakuraio.nk.core.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        return BaseResponse.<T>builder().code(0).message("").data(data).build();
    }
}
