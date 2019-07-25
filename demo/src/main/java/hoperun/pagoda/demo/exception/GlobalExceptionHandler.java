package hoperun.pagoda.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hoperun.pagoda.demo.bean.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	 /**
     * system exception handler
     * @param req request
     * @param resp response
     * @param e exception
     * @return BaseResponse BaseResponse
     * @throws Exception Exception
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<?> defaultErrorHandler( Exception e) throws Exception {
        logger.error("", e);
        @SuppressWarnings("rawtypes")
		BaseResponse<?> response = new BaseResponse();
        response.setErrorMsg(e.getMessage());;
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
        	response.setErrorCode("404");
        } else {
        	response.setErrorCode("500");
        response.setData(null);
        response.setStatus(0);
    }
        return response;

}
    
}
