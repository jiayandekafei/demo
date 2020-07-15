package hoperun.pagoda.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.RoleListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Role;
import hoperun.pagoda.demo.service.RoleService;
import io.swagger.annotations.ApiOperation;

/**
 * User Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    /**
     * User service.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Get User List.
     *
     * @return BaseResponse<UserListResponse> user list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve role list")
    public BaseResponse<RoleListResponse> retrieveRoleList() {
        final String method = "retrieveRoleList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get role list started");
        }

        List<Role> role = roleService.findAllRole();
        RoleListResponse response = new RoleListResponse(role);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get role list end");
        }

        return BaseResponse.ok(response);
    }
}
