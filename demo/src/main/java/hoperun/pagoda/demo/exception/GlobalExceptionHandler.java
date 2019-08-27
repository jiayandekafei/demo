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

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    HttpServletResponse response;
    /**
     * system exception handler
     * 
     * @param req
     *            request
     * @param resp
     *            response
     * @param e
     *            exception
     * @return BaseResponse BaseResponse
     * @throws Exception
     *             Exception
     */

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<?> defaultErrorHandler(Exception e) throws Exception {
        logger.error("", e);
        return BaseResponse.failure(ResultCode.SERVER_ERROR, e.getMessage());

    }

    /**
     * system exception handler
     * 
     * @param req
     *            request
     * @param resp
     *            response
     * @param e
     *            exception
     * @return BaseResponse BaseResponse
     * @throws Exception
     *             Exception
     */
    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse<?> businessException(BusinessException e) throws Exception {
        response.setStatus(e.getResponse().getCode());
        return e.getResponse();
    }
}
