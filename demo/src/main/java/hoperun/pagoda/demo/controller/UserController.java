package hoperun.pagoda.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.bean.UserResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.User;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.service.UserService;
import io.swagger.annotations.ApiOperation;

/**
 * User Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * User service.
     */
    @Autowired
    private UserService userService;

    /**
     * Get User List.
     *
     * @return BaseResponse<UserListResponse> user list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve user list")
    public BaseResponse<UserListResponse> retrieveUserList() {
        final String method = "retrieveUserList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user list started");
        }

        List<User> users = userService.findAllUser();
        UserListResponse response = new UserListResponse(users);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user list end");
        }

        return BaseResponse.ok(response);
    }

    /**
     * Get User List.
     *
     * @return BaseResponse<UserDetail> user reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get user by name")
    @GetMapping("/{username}")
    public BaseResponse<UserDetail> getUserByName(@PathVariable final String username) {
        final String method = "getUserByName";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user by name started");
        }

        UserDetail userDetail = userService.findUserByName(username);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user by name ended");
        }

        return BaseResponse.ok(userDetail);
    }

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
    public BaseResponse<UserResponse> register(@Valid @RequestBody final UserRegisterRequest userRequest) {
        final String method = "register";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "register user started");
        }

        // UserDetail userDetail = new UserDetail(userRequest.getUsername(), userRequest.getPassword(), Role.builder().id(1L).build());
        userService.register(userRequest);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "register user ended");
        }

        return BaseResponse.ok("successful");
    }

}
