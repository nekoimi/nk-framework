package com.sakuraio.nk.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>WebErrorController</p>
 * <p>
 * 由于使用 {@link org.springframework.web.bind.annotation.ExceptionHandler @ExceptionHandler} 无法捕获
 * {@link javax.servlet.Filter Filter} 产生的异常，现有处理方式不够优雅，这里我们自定义自己的错误处理
 * 使用自定义 {@link WebErrorController ErrorController}
 * 和自定义 {@link WebErrorAttributes ErrorAttributes} 的方式来捕获项目全局异常,
 * 此外，组合使用 {@link org.springframework.web.bind.annotation.ExceptionHandler @ExceptionHandler}
 * 注解来自定义业务异常，默认使用注解添加的异常处理器：{@link DefaultExceptionHandler}
 *
 * @author nekoimi 2022/10/16
 */
@Slf4j
@RequestMapping("${server.error.path:${error.path:/error}}")
public class WebErrorController extends AbstractErrorController {

    public WebErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    @ResponseBody
    public Map<String, Object> handleError(HttpServletRequest request) {
        return this.getErrorAttributes(request, ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.BINDING_ERRORS,
                ErrorAttributeOptions.Include.STACK_TRACE
        ));
    }
}
