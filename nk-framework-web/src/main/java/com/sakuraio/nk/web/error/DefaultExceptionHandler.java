package com.sakuraio.nk.web.error;

import com.sakuraio.nk.core.error.Errors;
import com.sakuraio.nk.core.error.exception.BaseRuntimeException;
import com.sakuraio.nk.core.error.vo.ErrorDetailsVO;
import com.sakuraio.nk.core.utils.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>DefaultExceptionHandler</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {
    // 当前上下文请求对象
    private final HttpServletRequest request;

    public DefaultExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * <p>包装异常信息</p>
     *
     * @param code    异常code码
     * @param message 异常提示
     * @param trace   异常详细信息，用于debug
     * @return
     */
    private ErrorDetailsVO wrapperErrorDetails(Integer code, String message, String trace) {
        return ErrorDetailsVO.of(code, request.getMethod(), request.getRequestURI(), message, trace);
    }

    @ExceptionHandler(value = BaseRuntimeException.class)
    public ErrorDetailsVO bizExceptionHandler(BaseRuntimeException e) {
        log.error("业务异常: {}", e.getMessage());
        return wrapperErrorDetails(e.getCode(), e.getMessage(), e.getTrace());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ErrorDetailsVO nullPointExceptionHandler(NullPointerException e) {
        log.error("空指针异常: {}", e.getMessage());
        return wrapperErrorDetails(Errors.SERVER_ERROR.code(), e.getMessage(), ErrorUtils.getTraceString(e));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ErrorDetailsVO handlerNotFoundExceptionHandler(NoHandlerFoundException e) {
        String errorMessage = "路由不存在: " + e.getHttpMethod() + ": " + e.getRequestURL();
        return wrapperErrorDetails(Errors.CLIENT_ERROR.code(), errorMessage, ErrorUtils.getTraceString(e));
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorDetailsVO defaultExceptionHandler(Exception e) {
        log.error("异常: {}, {}", e.getClass(), e.getMessage());
        e.printStackTrace();
        return wrapperErrorDetails(Errors.SERVER_ERROR.code(), e.getMessage(), ErrorUtils.getTraceString(e));
    }
}
