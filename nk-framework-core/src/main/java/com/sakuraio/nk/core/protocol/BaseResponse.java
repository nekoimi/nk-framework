package com.sakuraio.nk.core.protocol;

import com.sakuraio.nk.core.contract.ErrorDetails;
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
public class BaseResponse<T extends Serializable> implements Serializable {
    public static final Integer OK_CODE = 0;
    public static final String OK_MESSAGE = "OK";
    private static final Map<String, String> EMPTY_DATA = new HashMap<>();

    /**
     * <p>业务状态码；0 - 表示接口返回成功，其他值表示操作不成功，具体消息由message字段表示</p>
     */
    private Integer code;

    /**
     * <p>业务消息；当且仅当code不为0时有效</p>
     */
    private String message;

    /**
     * <p>详细错误信息，用于debug</p>
     */
    private String trace;

    /**
     * <p>业务数据</p>
     */
    private T data;

    /**
     * <p>返回成功</p>
     *
     * @param <T>
     * @return
     */
    public static <T extends Serializable> BaseResponse<T> ok() {
        return ok(null);
    }

    /**
     * <p>返回成功</p>
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T extends Serializable> BaseResponse<T> ok(T data) {
        return BaseResponse.<T>builder()
                .code(OK_CODE)
                .message(OK_MESSAGE)
                .data(data)
                .build();
    }

    /**
     * <p>返回错误</p>
     *
     * @param errorDetails
     * @param <T>
     * @return
     */
    public static <T extends Serializable> BaseResponse<T> error(ErrorDetails errorDetails) {
        return BaseResponse.<T>builder()
                .code(errorDetails.code())
                .message(errorDetails.message())
                .trace(errorDetails.trace())
                .build();
    }
}
