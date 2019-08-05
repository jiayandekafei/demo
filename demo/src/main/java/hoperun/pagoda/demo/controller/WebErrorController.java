package hoperun.pagoda.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.exception.ResultCode;

@RestController
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status == HttpStatus.FORBIDDEN.value()) {
            return BaseResponse.failure(ResultCode.FORBIDDEN);
        } else {
            return BaseResponse.failure(ResultCode.UNAUTHORIZED);
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}