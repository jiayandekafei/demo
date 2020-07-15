package hoperun.pagoda.demo.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hoperun.pagoda.demo.bean.BaseResponse;

/**
 * global exception hanlder.
 * @author zhangxiqin
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * http response.
     */
    @Autowired
    HttpServletResponse response;
    /**
     * system exception handler.
     * @param <T> <T>
     * 
     * @param e
     *            exception
     * @return BaseResponse BaseResponse
     */

    @SuppressWarnings("unchecked")
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> BaseResponse<T> defaultErrorHandler(final Exception e) {
        LOGGER.error("", e);
        return BaseResponse.failure(ResultCode.SERVER_ERROR, e.getMessage());

    }

    /**
     * system exception handler.
     * 
     * @param <T> <T>
     * @param e
     *            BusinessException
     * @return BaseResponse BaseResponse
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(value = BusinessException.class)
    public <T> BaseResponse<T> businessException(final BusinessException e) {
        response.setStatus(e.getResponse().getCode());
        return (BaseResponse<T>) e.getResponse();
    }
}
