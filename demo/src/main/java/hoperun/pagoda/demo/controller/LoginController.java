package hoperun.pagoda.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.LoginResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.service.UserService;
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

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private LoginServiceImpl loginService;

    /**
     * User service.
     */
    @Autowired
    private UserService userService;

    /**
     * register user.
     *
     * @param userRequest
     *            user request
     * @return UserResponse user response
     */
    @SuppressWarnings("unchecked")
    @PostMapping(value = "/register")
    @ApiOperation(value = "register")
    public BaseResponse<UserDetailResponse> register(@Valid @RequestBody final UserRegisterRequest request) {
        final String method = "register";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "register user started");
        }

        userService.register(request);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "register user ended");
        }

        return BaseResponse.ok("successful");
    }

    @SuppressWarnings("unchecked")
    @PostMapping(value = "/login")
    @ApiOperation(value = "login")
    @ApiResponses({@ApiResponse(code = 500, message = "", response = BaseResponse.class)})
    public BaseResponse<LoginResponse> login(@RequestBody UserRequest user) {
        final LoginResponse response = loginService.login(user.getUsername(), user.getPassword());
        return BaseResponse.ok(response);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/logout")
    @ApiOperation(value = "logout", notes = "logout")
    public BaseResponse<UserDetailResponse> logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return BaseResponse.failure(ResultCode.UNAUTHORIZED);
        }
        loginService.logout(token);
        return BaseResponse.ok();
    }

}
