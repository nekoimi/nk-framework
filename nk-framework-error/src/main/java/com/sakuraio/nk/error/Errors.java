package com.sakuraio.nk.error;

import com.sakuraio.nk.core.contract.ErrorDetails;

/**
 * <p>Errors</p>
 *
 * @author nekoimi 2022/10/03
 */
public enum Errors implements ErrorDetails {
    // OK
    OK(0, "ok"),
    // 客户端请求异常
    CLIENT_ERROR(190400, "无效的请求！"),
    // 未捕获的异常，系统发生致命错误，提示系统维护更新!
    SERVER_ERROR(190500, "系统更新中！请稍候再试～");;

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
