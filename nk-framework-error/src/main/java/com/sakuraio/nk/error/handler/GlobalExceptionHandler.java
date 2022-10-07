package com.sakuraio.nk.error.handler;

import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.error.Errors;
import com.sakuraio.nk.error.exception.BizException;
import com.sakuraio.nk.error.utils.ErrorUtils;
import com.sakuraio.nk.error.vo.ErrorDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>GlobalExceptionHandler</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public BaseResponse<ErrorDetailsVO> bizExceptionHandler(BizException e) {
        log.error("业务异常: {}", e.getMessage());
        e.printStackTrace();
        return BaseResponse.error(ErrorDetailsVO.of(
                e.getCode(),
                e.getMessage(),
                e.getTrace()
        ));
    }

    @ExceptionHandler(value = NullPointerException.class)
    public BaseResponse<ErrorDetailsVO> nullPointExceptionHandler(NullPointerException e) {
        log.error("空指针异常: {}", e.getMessage());
        e.printStackTrace();
        return BaseResponse.error(ErrorDetailsVO.of(
                Errors.SERVER_ERROR.code(),
                e.getMessage(),
                ErrorUtils.getTraceString(e)
        ));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public BaseResponse<ErrorDetailsVO> handlerNotFoundExceptionHandler(NoHandlerFoundException e) {
        String errorMessage = "路由不存在! method: " + e.getHttpMethod() + ", url: " + e.getRequestURL();
        return BaseResponse.error(ErrorDetailsVO.of(
                Errors.CLIENT_ERROR.code(),
                errorMessage,
                ErrorUtils.getTraceString(e)
        ));
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse<ErrorDetailsVO> defaultExceptionHandler(Exception e) {
        log.error("异常: {}, {}", e.getClass(), e.getMessage());
        e.printStackTrace();
        return BaseResponse.error(ErrorDetailsVO.of(
                Errors.SERVER_ERROR.code(),
                e.getMessage(),
                ErrorUtils.getTraceString(e)
        ));
    }
}
