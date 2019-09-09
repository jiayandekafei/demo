package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserGroupsResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.constant.Constant;
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
    public BaseResponse<UserListResponse> retrieveUserList(@RequestParam int userId, @RequestParam String superuser) {
        final String method = "retrieveUserList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user list started");
        }

        UserListResponse response = userService.findAllUser(userId, superuser);

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
    @ApiOperation(value = "get user by user id")
    @GetMapping("/{userId}")
    public BaseResponse<UserDetailResponse> getUserById(@PathVariable final String userId) {
        final String method = "getUserById";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user by id started");
        }

        UserDetailResponse userDetail = userService.findUserByID(userId);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user by id ended");
        }

        return BaseResponse.ok(userDetail);
    }

    /**
     * update User List.
     *
     * @return BaseResponse<UserDetail> user reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update user by user id")
    @PutMapping("/{userId}")
    public BaseResponse<String> updateUserById(@PathVariable final int userId, @RequestBody UserRequest request) {
        final String method = "getUserById";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update user by id started");
        }

        userService.update(request, userId);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update user by id ended");
        }

        return BaseResponse.ok("successful!");
    }

    /**
     * Get User List.
     *
     * @return BaseResponse<UserDetail> user reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get user by user id")
    @GetMapping("/checkUser/{username}")
    public BaseResponse<String> isUserExist(@PathVariable final String username) {
        final String method = "getUserByName";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user by id started");
        }

        boolean exist = userService.isUserExist(username);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user by id ended");
        }

        return BaseResponse.ok(exist);
    }

    @PostMapping("")
    public String addUser(UserRequest user) {
        return userService.insert(user);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        return userService.delete(userId);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get group tree")
    @GetMapping("/grouptree/{userId}")
    public BaseResponse<UserGroupsResponse> getGroupTree(@PathVariable("userId") Integer userId) {
        final String method = "getGroupTree";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user group tree started");
        }

        return BaseResponse.ok(userService.getGroupTree(userId));
    }

}
