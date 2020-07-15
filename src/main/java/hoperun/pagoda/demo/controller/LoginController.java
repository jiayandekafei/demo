package hoperun.pagoda.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.LoginRequest;
import hoperun.pagoda.demo.bean.LoginResponse;
import hoperun.pagoda.demo.bean.MessageResponse;
import hoperun.pagoda.demo.bean.StatisticsResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.service.LoginService;
import io.swagger.annotations.ApiOperation;

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

    /**
     * token header.
     */
    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * login service.
     */
    @Autowired
    private LoginService loginService;

    /**
     * register user.
     *
     * @param request
     *            register request
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

        loginService.register(request);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "register user ended");
        }

        return BaseResponse.ok("successful");
    }

    /**
     * login.
     * @param user login request.
     * @return BaseResponse<LoginResponse> login response.
     */
    @SuppressWarnings("unchecked")
    @PostMapping(value = "/login")
    @ApiOperation(value = "login")
    public BaseResponse<LoginResponse> login(@RequestBody final LoginRequest user) {
        final String method = "login";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "login user started");
        }
        return BaseResponse.ok(loginService.login(user.getUsername(), user.getPassword()));
    }

    /**
     * retrieve message.
     * @param username user name.
     * @return BaseResponse<MessageResponse> message response.
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = "/message")
    @ApiOperation(value = "message")
    public BaseResponse<List<MessageResponse>> retrieveMessage(@RequestParam final String username) {
        final String method = "retrieveMessage";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve message started");
        }
        return BaseResponse.ok(loginService.retrieveMessage(username));
    }

    /**
     * retrieve message.
     * @return BaseResponse<StatisticsResponse> login response.
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = "/statistics")
    @ApiOperation(value = "statistics")
    public BaseResponse<StatisticsResponse> retrieveStatisticsInfo() {
        final String method = "retrieveStatisticsInfo";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve statistics started");
        }
        return BaseResponse.ok(loginService.retrieveStatisticsInfo());
    }

    /**
     * log out.
     * @param request log out request.
     * @return BaseResponse<LoginResponse> login response.
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = "/signout")
    @ApiOperation(value = "logout", notes = "logout")
    public BaseResponse<T> logout(final HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return BaseResponse.failure(ResultCode.UNAUTHORIZED);
        }
        loginService.logout(token);
        return BaseResponse.ok();
    }

}
