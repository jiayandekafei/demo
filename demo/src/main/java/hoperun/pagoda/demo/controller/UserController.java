package hoperun.pagoda.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.DeleteMutiUserRequest;
import hoperun.pagoda.demo.bean.DeleteUserRequest;
import hoperun.pagoda.demo.bean.PasswordRequest;
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
     * retireve user list.
     * @param userId userId
     * @param superuser is superuser
     * @param pageNo page No
     * @param limit the display num for each page
     * @param name user name
     * @return BaseResponse<UserListResponse> user list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve user list")
    public BaseResponse<UserListResponse> retrieveUserList(@RequestParam final int userId, @RequestParam final String superuser,
            @RequestParam final int pageNo, @RequestParam final int limit, @RequestParam final String name) {
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
     * Get User by user id.
     * @param userId user id
     * @return BaseResponse<UserDetailResponse> user reponse
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
     * update user List.
     * @param request user info
     * @return BaseResponse<String> user reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update user by user id")
    @PutMapping("")
    public BaseResponse<String> updateUserById(@RequestBody final UserRequest request) {
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

    /**
     * add user List.
     * @param request user info
     * @return BaseResponse<UserDetail> user reponse
     */
    @SuppressWarnings("unchecked")
    @PostMapping("")
    public BaseResponse<String> addUser(@RequestBody final UserRequest request) {
        userService.insert(request);
        return BaseResponse.ok();
    }

    /**
     * delete User.
     * @param user delete user request
     * @return BaseResponse<String> user reponse
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/delete")
    public BaseResponse<String> deleteUser(@RequestBody final DeleteUserRequest user) {

        return BaseResponse.ok(userService.delete(user.getUserId(), user.getGroupId(), user.getGroupLength()));
    }

    /**
     * delete mutil User List.
     * @param request user info
     * @return BaseResponse<String> user reponse
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/deleteBatch")
    public BaseResponse<String> batchDeleteUser(@RequestBody final DeleteMutiUserRequest request) {
        List<DeleteUserRequest> users = request.getUsers();
        for (DeleteUserRequest user : users) {
            userService.delete(user.getUserId(), user.getGroupId(), user.getGroupLength());
        }
        return BaseResponse.ok(Constant.SUCCESS_MESSAGE);
    }

    /**
     * get group tree by user id.
     * @param userId user id
     * @return BaseResponse<UserGroupsResponse> group tree
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get group tree")
    @GetMapping("/grouptree/{userId}")
    public BaseResponse<UserGroupsResponse> getGroupTree(@PathVariable("userId") final Integer userId) {
        final String method = "getGroupTree";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get user group tree started");
        }

        return BaseResponse.ok(userService.getGroupTree(userId));
    }

    /**
     * juge wether the user exist.
     * @param username user name
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

    /**
     * approve.
     * @param req user status info
     * @return BaseResponse<UserGroupsResponse>
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "approve user")
    @PostMapping("/approve")
    public BaseResponse<UserGroupsResponse> approveUser(@RequestBody final UserRequest req) {
        final String method = "approveUser";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "approve new user apply started");
        }
        userService.approveUser(req);
        return BaseResponse.ok(Constant.SUCCESS_MESSAGE);
    }

    /**
     * reject.
     * @param req user status info
     * @return BaseResponse<UserGroupsResponse>
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "reject user")
    @PostMapping("/reject")
    public BaseResponse<UserGroupsResponse> rejectUser(@RequestBody final UserRequest req) {
        final String method = "rejectUser";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "reject new user apply started");
        }
        userService.rejectUser(req);
        return BaseResponse.ok(Constant.SUCCESS_MESSAGE);
    }

    /**
     * update password.
     * @param req updte request
     * @return updated message
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update user password")
    @PostMapping("/password")
    public BaseResponse<UserGroupsResponse> updateUserPassword(@RequestBody final PasswordRequest req) {
        final String method = "updateUserStatus";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update user password started");
        }

        return BaseResponse.ok(userService.updateUserPassword(req.getUserId(), req.getPassword()));
    }

    /**
     * check password.
     * @param userId user id.
     * @param password password
     * @return BaseResponse<UserGroupsResponse>
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "check password")
    @GetMapping("/password")
    public BaseResponse<UserGroupsResponse> checkUserPassword(@RequestParam final int userId, @RequestParam final String password) {
        final String method = "checkUserPassword";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "check user password started");
        }

        return BaseResponse.ok(userService.isPasswordSame(userId, password));
    }
}
