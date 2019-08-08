package hoperun.pagoda.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.bean.UserResponse;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.service.impl.LoginServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Login Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
public class LoginController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private LoginServiceImpl loginService;

    @SuppressWarnings("unchecked")
    @PostMapping(value = "/login")
    @ApiOperation(value = "login")
    @ApiResponses({@ApiResponse(code = 500, message = "", response = BaseResponse.class)})
    public BaseResponse<UserResponse> login(@RequestBody UserRequest user) {
        final UserResponse response = loginService.login(user.getUsername(), user.getPassword());
        return BaseResponse.ok(response);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/logout")
    @ApiOperation(value = "logout", notes = "logout")
    public BaseResponse<UserResponse> logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return BaseResponse.failure(ResultCode.UNAUTHORIZED);
        }
        loginService.logout(token);
        return BaseResponse.ok();
    }

}
