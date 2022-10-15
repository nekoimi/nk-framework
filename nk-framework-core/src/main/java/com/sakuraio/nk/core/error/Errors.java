package com.sakuraio.nk.core.error;

import com.sakuraio.nk.core.contract.ErrorDetails;

/**
 * <p>Errors</p>
 *
 * @author nekoimi 2022/10/03
 */
public enum Errors implements ErrorDetails {
    // OK
    OK(0, "ok"),

    // 和 Http 相近的异常， code 从 10000 开始
    REQUEST_VALIDATE_EXCEPTION(10004, "Invalid request"),
    AUTH_TYPE_NOT_SUPPORTS_EXCEPTION(10102, "认证类型不支持"),
    TOKEN_EXPIRED_EXCEPTION(10127, "认证信息已过期"),
    TOKEN_CANNOT_BE_REFRESH_EXCEPTION(10127, "Token cannot be refresh"),
    TOKEN_CANNOT_BE_PARSE_EXCEPTION(10127, "Token cannot be parse"),
    TOKEN_HAS_BEEN_BLACKLISTED_EXCEPTION(10127, "The token has been blacklisted"),
    TOKEN_BLACK_EXCEPTION(10127, "Identity information has expired"),
    AUTHENTICATION_EXCEPTION(10401, "Not Authenticated"),
    ACCESS_DENIED_EXCEPTION(10501, "Access is denied"),
    INJECT_REQUEST_ATTRIBUTE_ERROR_EXCEPTION(10601, "Inject request attribute error!"),

    HTTP_STATUS_BAD_REQUEST(10400, "Bad request"),
    HTTP_STATUS_UNAUTHORIZED(10401, "Unauthorized"),
    HTTP_STATUS_FORBIDDEN(10403, "Forbidden"),
    HTTP_STATUS_NOT_FOUND(10404, "Not found"),
    HTTP_STATUS_METHOD_NOT_ALLOWED(10405, "Method not allowed"),

    // 业务相关异常，code 从 20000 开始往上加
    FAILED_TO_ENCODER_ERROR_EXCEPTION(20001, "Encoder error"),
    FAILED_TO_OPERATION_ERROR_EXCEPTION(20001, "操作失败"),
    FAILED_TO_NOT_FOUND_ERROR_EXCEPTION(20002, "Resource not found"),
    FAILED_TO_CREATE_ERROR_EXCEPTION(20003, "Failed to create resource"),
    FAILED_TO_UPDATE_ERROR_EXCEPTION(20004, "Failed to update resource"),

    // 底层中间件相关异常，code 从 50000 开始
    ID_GENERATOR_SYSTEM_CLOCK_EXCEPTION(50000, "System clock error"),
    REDIS_ERROR_EXCEPTION(50001, "Redis error"),
    NOT_IMPLEMENTED_ERROR_EXCEPTION(50002, "Not implemented"),

    // 客户端请求异常
    CLIENT_ERROR(99400, "无效的请求！"),
    // 未捕获的异常，系统发生致命错误，提示系统维护更新!
    SERVER_ERROR(99500, "系统更新中！请稍候再试～");;

    private final Integer code;
    private final String message;

    Errors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
