package hoperun.pagoda.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.exception.ResultCode;

/**
 * web error controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("")
public class WebErrorController implements ErrorController {

    /**
     * web error service.
     * @param request request
     * @param response response
     * @return error
     */
    @GetMapping("/error")
    public final Object error(final HttpServletRequest request, final HttpServletResponse response) {
        int status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (status) {
            case Constant.STATUS_CODE_401 :
                return BaseResponse.failure(ResultCode.UNAUTHORIZED);
            case Constant.STATUS_CODE_403 :
                return BaseResponse.failure(ResultCode.FORBIDDEN);
            case Constant.STATUS_CODE_404 :
                return BaseResponse.failure(ResultCode.NOT_FOUND);
            default :
                return BaseResponse.failure(ResultCode.SERVER_ERROR);
        }
    }

    @Override
    public final String getErrorPath() {
        return "/error";
    }
}