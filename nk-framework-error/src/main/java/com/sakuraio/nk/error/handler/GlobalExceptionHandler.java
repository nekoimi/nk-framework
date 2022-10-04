package com.sakuraio.nk.error.handler;

import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.error.Errors;
import com.sakuraio.nk.error.exception.BizException;
import com.sakuraio.nk.error.utils.ErrorUtils;
import com.sakuraio.nk.error.vo.ErrorDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>GlobalExceptionHandler</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public BaseResponse<ErrorDetailsVO> bizExceptionHandler(BizException exception) {
        log.error("业务异常: {}", exception.getMessage());
        return BaseResponse.error(ErrorDetailsVO.of(
                exception.getCode(),
                exception.getMessage(),
                exception.getTrace()
        ));
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public BaseResponse<ErrorDetailsVO> nullPointExceptionHandler(NullPointerException exception) {
        log.error("空指针异常: {}", exception.getMessage());
        return BaseResponse.error(ErrorDetailsVO.of(
                Errors.SERVER_ERROR.code(),
                exception.getMessage(),
                ErrorUtils.getTraceString(exception)
        ));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse<ErrorDetailsVO> defaultExceptionHandler(Exception exception) {
        log.error("异常: {}", exception.getMessage());
        return BaseResponse.error(ErrorDetailsVO.of(
                Errors.SERVER_ERROR.code(),
                exception.getMessage(),
                ErrorUtils.getTraceString(exception)
        ));
    }
}
