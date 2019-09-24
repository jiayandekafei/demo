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
import hoperun.pagoda.demo.bean.DeleteGroupRequest;
import hoperun.pagoda.demo.bean.GroupListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Group;
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

    /**
     * Get Group List.
     *
     * @return BaseResponse<GroupDetail> Group reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get Group by Group id")
    @GetMapping("/{groupId}")
    public BaseResponse<Group> getGroupById(@PathVariable final int groupId) {
        final String method = "getGroupById";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Group by id started");
        }

        return BaseResponse.ok(groupService.findByGrupId(groupId));
    }

    /**
     * update Group List.
     *
     * @return BaseResponse<group> Group reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update Group by Group id")
    @PutMapping("")
    public BaseResponse<String> updateGroupById(@RequestBody Group request) {
        final String method = "updateGroupById";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update Group by id started");
        }

        return BaseResponse.ok(groupService.update(request));
    }

    @SuppressWarnings("unchecked")
    @PostMapping("")
    public BaseResponse<String> addGroup(@RequestBody Group request) {

        return BaseResponse.ok(groupService.create(request));
    }

    @SuppressWarnings("unchecked")
    @DeleteMapping("/{groupId}")
    public BaseResponse<String> deleteGroup(@PathVariable("groupId") Integer groupId) {
        groupService.delete(groupId);
        return BaseResponse.ok("successfully!");
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/deleteBatch")
    public BaseResponse<String> batchDeleteGroup(@RequestBody DeleteGroupRequest request) {
        String[] ids = request.getGroupIds().split(",");
        List<Integer> groups = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            groups.add(Integer.parseInt(ids[i]));
        }
        groupService.batchDelete(groups);
        return BaseResponse.ok("successfully!");
    }

    /**
     * check .
     *
     * @return BaseResponse<GroupDetail> Group reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get Group by Group id")
    @GetMapping("/checkGroup/{groupname}")
    public BaseResponse<String> isGroupExist(@PathVariable final String groupname) {
        final String method = "isGroupExist";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Group by id started");
        }

        return BaseResponse.ok(groupService.isGroupExist(groupname));
    }

}
