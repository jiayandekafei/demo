package hoperun.pagoda.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.bean.UserResponse;
import hoperun.pagoda.demo.entity.Role;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Get User List.
     *
     * @return
     */
    @ApiOperation(value = "retrieve user list")
    @GetMapping("/list")
    public BaseResponse<UserListResponse> userList() {
        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "userList", "get user list started");
        }

        List<User> users = userService.findAllUser();
        UserListResponse response = new UserListResponse(users);

        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "userList", "get user list ended");
        }

        return BaseResponse.ok(response);
    }

    @GetMapping(value = "/sign")
    @ApiOperation(value = "sign up")
    public BaseResponse<UserResponse> sign(@Valid @RequestBody UserRequest userRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "sign", "register user started");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "sign", "register user ended");
        }
        UserDetail userDetail = new UserDetail(userRequest.getUsername(), userRequest.getPassword(), Role.builder().id(1l).build());

        return BaseResponse.ok(userService.register(userDetail));
    }

    @PostMapping(value = "/sign")
    @ApiOperation(value = "sign up")
    public BaseResponse<UserResponse> getUserByName(@Valid @RequestBody UserRequest userRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "sign", "register user started");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "sign", "register user ended");
        }
        UserDetail userDetail = new UserDetail(userRequest.getUsername(), userRequest.getPassword(), Role.builder().id(1l).build());

        return BaseResponse.ok(userService.register(userDetail));
    }
}
