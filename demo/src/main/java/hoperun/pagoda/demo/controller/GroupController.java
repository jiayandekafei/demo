package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.GroupListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.GroupService;
import io.swagger.annotations.ApiOperation;

/**
 * User Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    /**
     * User service.
     */
    @Autowired
    private GroupService groupService;

    /**
     * Get User List.
     *
     * @return BaseResponse<UserListResponse> user list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve group list")
    public BaseResponse<GroupListResponse> retrieveGroupList(@RequestParam int userId, @RequestParam String superuser, @RequestParam int pageNo,
            @RequestParam int limit, @RequestParam String name) {
        final String method = "retrieveGroupList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get group list started");
        }

        GroupListResponse response = groupService.findAllGroup(userId, superuser, pageNo, limit, name, false);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get group list end");
        }

        return BaseResponse.ok(response);
    }
}
