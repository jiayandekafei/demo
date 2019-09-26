package hoperun.pagoda.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
import hoperun.pagoda.demo.bean.DeleteUserRequest;
import hoperun.pagoda.demo.bean.PasswordRequest;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserGroupsResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.bean.UserStatusRequest;
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
    public BaseResponse<UserListResponse> retrieveUserList(@RequestParam int userId, @RequestParam String superuser, @RequestParam int pageNo,
            @RequestParam int limit, @RequestParam String name) {
        final String method = "retrieveUserList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user list started");
        }

        UserListResponse response = userService.findAllUser(userId, superuser, pageNo, limit, name);

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
    public BaseResponse<UserDetailResponse> getUserById(@PathVariable final int userId) {
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
    @PutMapping("")
    public BaseResponse<String> updateUserById(@RequestBody UserRequest request) {
        final String method = "updateUserById";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update user by id started");
        }

        userService.update(request);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update user by id ended");
        }

        return BaseResponse.ok("successful!");
    }

    @SuppressWarnings("unchecked")
    @PostMapping("")
    public BaseResponse<String> addUser(@RequestBody UserRequest user) {

        return BaseResponse.ok(userService.insert(user));
    }

    @SuppressWarnings("unchecked")
    @DeleteMapping("/{userId}")
    public BaseResponse<String> deleteUser(@PathVariable("userId") Integer userId) {

        return BaseResponse.ok(userService.delete(userId));
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/deleteBatch")
    public BaseResponse<String> batchDeleteUser(@RequestBody DeleteUserRequest request) {
        String[] ids = request.getUserIds().split(",");
        List<Integer> users = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            users.add(Integer.parseInt(ids[i]));

        }
        return BaseResponse.ok(userService.deleteMultiUser(users));
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

    /**
     * Get User List.
     *
     * @return BaseResponse<UserDetail> user reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get user by user id")
    @GetMapping("/check/{username}")
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

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update user status")
    @PostMapping("/status")
    public BaseResponse<UserGroupsResponse> updateUserStatus(@RequestBody UserStatusRequest req) {
        final String method = "updateUserStatus";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update user status started");
        }

        return BaseResponse.ok(userService.updateUserStatus(req.getStatus(), req.getUserId()));
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update user status")
    @PostMapping("/password")
    public BaseResponse<UserGroupsResponse> updateUserPassword(@RequestBody PasswordRequest req) {
        final String method = "updateUserStatus";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update user password started");
        }

        return BaseResponse.ok(userService.updateUserPassword(req.getUserId(), req.getPassword()));
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update user status")
    @GetMapping("/password")
    public BaseResponse<UserGroupsResponse> checkUserPassword(@RequestParam int userId, @RequestParam String password) {
        final String method = "checkUserPassword";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "check user password started");
        }

        return BaseResponse.ok(userService.isPasswordSame(userId, password));
    }
}
