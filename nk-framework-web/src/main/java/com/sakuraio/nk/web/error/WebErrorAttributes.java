package com.sakuraio.nk.web.error;

import com.sakuraio.nk.core.contract.ErrorDetails;
import com.sakuraio.nk.core.error.Errors;
import com.sakuraio.nk.core.utils.ErrorUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>WebErrorAttributes</p>
 * <p>
 * 自定义异常返回，自定义参考：{@link org.springframework.boot.web.servlet.error.DefaultErrorAttributes}
 *
 * @author nekoimi 2022/10/17
 */
public class WebErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {
    private static final String ERROR_INTERNAL_ATTRIBUTE = WebErrorAttributes.class.getName() + ".ERROR";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        extractCode(errorAttributes, webRequest);
        extractHttpInfo(errorAttributes, webRequest);
        extractErrorMessage(errorAttributes, webRequest);
        return errorAttributes;
    }

    private void extractCode(Map<String, Object> errorAttributes, WebRequest webRequest) {
        Integer status = getAttribute(webRequest, RequestDispatcher.ERROR_STATUS_CODE);
        if (status == null) {
            errorAttributes.put(ErrorDetails.PROP_CODE, Errors.SERVER_ERROR.code());
        } else {
            errorAttributes.put(ErrorDetails.PROP_CODE, status);
        }
    }

    private void extractHttpInfo(Map<String, Object> errorAttributes, WebRequest webRequest) {
        String path = getAttribute(webRequest, RequestDispatcher.ERROR_REQUEST_URI);
        if (path != null) {
            if (webRequest instanceof ServletWebRequest) {
                errorAttributes.put(ErrorDetails.PROP_HTTP_METHOD, ((ServletWebRequest) webRequest).getHttpMethod().name());
            }
            errorAttributes.put(ErrorDetails.PROP_HTTP_PATH, path);
        }
    }

    private void extractErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest) {
        Throwable error = getError(webRequest);
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
            if (error instanceof BindingResult) {
                // 参数绑定异常
                BindingResult bindingResult = (BindingResult) error;
                if (bindingResult.hasErrors()) {
                    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                    StringBuilder errorBuilder = new StringBuilder("请求参数错误: ");
                    for (FieldError fieldError : fieldErrors) {
                        errorBuilder.append(fieldError.getField());
                        errorBuilder.append(fieldError.getDefaultMessage());
                        errorBuilder.append("; ");
                    }
                    errorAttributes.put(ErrorDetails.PROP_MESSAGE, errorBuilder.toString());
                }
            } else if (error instanceof NoHandlerFoundException) {
                NoHandlerFoundException routeNotFoundError = (NoHandlerFoundException) error;
                String errorMessage = "路由不存在: " + routeNotFoundError.getHttpMethod() + ": " + routeNotFoundError.getRequestURL();
                errorAttributes.put(ErrorDetails.PROP_MESSAGE, errorMessage);
            } else {
                if (StringUtils.hasLength(error.getMessage())) {
                    errorAttributes.put(ErrorDetails.PROP_MESSAGE, error.getMessage());
                } else {
                    Object message = getAttribute(webRequest, RequestDispatcher.ERROR_MESSAGE);
                    if (message != null && StringUtils.hasLength(message.toString())) {
                        errorAttributes.put(ErrorDetails.PROP_MESSAGE, message.toString());
                    }
                }
            }
            errorAttributes.put(ErrorDetails.PROP_TRACE, ErrorUtils.getTraceString(error));
        } else {
            errorAttributes.put(ErrorDetails.PROP_MESSAGE, "No message available");
        }
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.setAttribute(ERROR_INTERNAL_ATTRIBUTE, ex);
        return null;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        Throwable exception = getAttribute(webRequest, ERROR_INTERNAL_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(webRequest, RequestDispatcher.ERROR_EXCEPTION);
        }
        // store the exception in a well-known attribute to make it available to metrics
        // instrumentation.
        webRequest.setAttribute(ErrorAttributes.ERROR_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        return exception;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
