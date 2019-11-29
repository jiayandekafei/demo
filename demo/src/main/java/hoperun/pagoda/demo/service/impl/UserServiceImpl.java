package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.GroupNode;
import hoperun.pagoda.demo.entity.Message;
import hoperun.pagoda.demo.entity.RoleNode;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroup;
import hoperun.pagoda.demo.entity.UserGroupBean;
import hoperun.pagoda.demo.entity.UserGroupTree;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.mapper.MessageMapper;
import hoperun.pagoda.demo.mapper.UserMapper;
import hoperun.pagoda.demo.service.GroupService;
import hoperun.pagoda.demo.service.RoleService;
import hoperun.pagoda.demo.service.UserService;
import io.jsonwebtoken.lang.Collections;

/**
 * user  service.
 * @author zhangxiqin
 *
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * userMapper.
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * messge Mapper.
     */
    @Autowired
    private MessageMapper messageMapper;
    /**
     * groupService.
     */
    @Autowired
    private GroupService groupService;
    /**
     * roleService.
     */
    @Autowired
    private RoleService roleService;

    /**
     * add user.
     */
    @Override
    @Transactional
    public void insert(final UserRequest user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetail userDetail = new UserDetail(user.getUsername(), encoder.encode("000000"), user.getEmail(), user.getJobTitle(), "N", "A");
        // insert user bace info
        userMapper.insert(userDetail);
        // add user group and role under the group
        List<UserGroup> groups = user.getGroups();
        if (!Collections.isEmpty(groups)) {
            for (UserGroup userGroup : groups) {
                userMapper.insertUserGroup(userDetail.getUserId(), userGroup.getGroupId(), userGroup.getRoleId());
            }
        }
    }

    /**
     * update user.
     */
    @Override
    @Transactional
    public void update(final UserRequest request) {
        userMapper.update(request);
        List<UserGroup> userGroups = request.getGroups();
        List<Integer> groupIds = new ArrayList<>();
        // update user group role table
        for (UserGroup userGroup : userGroups) {
            // check weather the group exist
            UserGroup group = userMapper.findUserGroupByUserIdAndGroupId(request.getUserId(), userGroup.getGroupId());
            if (null == group) {
                userMapper.insertUserGroup(request.getUserId(), userGroup.getGroupId(), userGroup.getRoleId());
            } else {
                userMapper.updateUserGroup(request.getUserId(), userGroup.getGroupId(), userGroup.getRoleId());
            }
            groupIds.add(userGroup.getGroupId());
        }
        // check if has other groups
        List<Integer> groups = userMapper.findUserGroupByGroupIds(request.getUserId(), groupIds);
        // delete group
        if (!Collections.isEmpty(groups)) {
            userMapper.deleteUserGroupByUserIdAndGroups(groups, request.getUserId());
        }
    }

    /**
     * retrieve all user.
     */
    @Override
    public UserListResponse findAllUser(final int userId, final String superuser, final int pageNo, final int limit, final String name,
            final List<Integer> currentGroups) {
        List<UserDetailResponse> userDetailList = new ArrayList<>();
        List<UserDetail> users = userMapper.findAllUser();
        // filter by group
        filterUserByGroup(superuser, currentGroups, userDetailList, users);
        // filter by name
        if (!StringUtils.isEmpty(name)) {
            userDetailList = userDetailList.stream().filter(user -> user.getUsername().contains(name)).collect(Collectors.toList());
        }
        int size = userDetailList.size();
        // filter by pageNo and limit
        userDetailList = userDetailList.stream().skip((pageNo - 1) * (long) limit).limit(limit).collect(Collectors.toList());

        return new UserListResponse(userDetailList, size);
    }

    /**
     * filter user by group.
     * @param superuser if super user 
     * @param currentGroups currentGroups
     * @param userDetailList userDetailList
     * @param users users
     */
    private void filterUserByGroup(final String superuser, final List<Integer> currentGroups, final List<UserDetailResponse> userDetailList,
            final List<UserDetail> users) {
        // groupMap
        Map<Integer, String> groupMap = groupService.getGroupMap();
        // roleMap
        Map<Integer, String> roleMap = roleService.getRoleMap();
        // if super user ,return all users,otherwise fliter by group id.
        for (UserDetail user : users) {
            List<UserGroup> groups = userMapper.findUserGroups(user.getUserId());
            if (!groups.isEmpty()) {
                for (UserGroup userGroup : groups) {
                    if ("Y".equals(superuser) || currentGroups.contains(userGroup.getGroupId())) {
                        UserDetailResponse userDetail = new UserDetailResponse(user.getUserId(), user.getUsername(), user.getStatus(),
                                user.getEmail(), user.getJobTitle(), user.getSuperuser(), user.getPhoto(),
                                getGroupList(userGroup, groupMap.get(userGroup.getGroupId()), roleMap.get(userGroup.getRoleId())), groups.size());
                        userDetailList.add(userDetail);
                    }
                }
            } else {
                UserDetailResponse userDetail = new UserDetailResponse(user.getUserId(), user.getUsername(), user.getStatus(), user.getEmail(),
                        user.getJobTitle(), user.getSuperuser(), user.getPhoto(), new ArrayList<>(), 0);
                userDetailList.add(userDetail);
            }
        }
    }

    /**
     * Get Group.
     * @param userGroup userGroup 
     * @param groupName groupName 
     * @param roleName roleName 
     * @return group list
     */
    private List<UserGroupBean> getGroupList(final UserGroup userGroup, final String groupName, final String roleName) {
        List<UserGroupBean> groupList = new ArrayList<>();
        UserGroupBean group = new UserGroupBean(userGroup.getGroupId(), groupName, userGroup.getRoleId(), roleName);
        groupList.add(group);
        return groupList;
    }

    /**
     * retrieve user info by name.
     */
    @Override
    public UserDetail findUserByName(final String username) {
        UserDetail user = userMapper.findByUsername(username);
        if (null == user) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User dose not exist!"));
        }
        return userMapper.findByUsername(username);
    }

    /**
     * retrieve user info by id.
     */
    @Override
    public UserDetailResponse findUserByID(final int userId) {
        UserDetailResponse userDetail;
        UserDetail user = userMapper.findByUserId(userId);
        if (null != user) {
            // groupMap
            Map<Integer, String> groupMap = groupService.getGroupMap();
            // roleMap
            Map<Integer, String> roleMap = roleService.getRoleMap();
            List<UserGroup> groups = userMapper.findUserGroups(user.getUserId());
            List<UserGroupBean> groupList = new ArrayList<>();
            for (UserGroup group : groups) {
                groupList.addAll(getGroupList(group, groupMap.get(group.getGroupId()), roleMap.get(group.getRoleId())));
            }
            userDetail = new UserDetailResponse(user.getUserId(), user.getUsername(), user.getStatus(), user.getEmail(), user.getJobTitle(),
                    user.getSuperuser(), user.getPhoto(), groupList, groupList.size());
        } else {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User dose not exist!"));
        }
        return userDetail;
    }

    /**
     * juge wether user exist.
     */
    @Override
    public boolean isUserExist(final String username) {
        UserDetail user = userMapper.findByUsername(username);

        return null != user;
    }

    /**
     * retrieve uer group trees.
     */
    @Override
    public List<UserGroupTree> getGroupTree(final List<Integer> currentGroup, final String superuser, final int type, final int groupId,
            final int roleId) {
        // groupMap
        Map<Integer, String> groupMap = groupService.getGroupMap();
        // roleMap
        Map<Integer, String> roleMap = roleService.getRoleMap();
        if ("Y".equals(superuser)) {
            return getSuperUserGroupTree(groupMap, roleMap, type, groupId, roleId);
        } else {
            return getGroupTree(groupMap, roleMap, currentGroup, type, groupId, roleId);
        }
    }

    /**
     * retrieve group tree for super user.  
     * @param groupMap groupMap
     * @param roleMap roleMap
     * @param type type
     * @param groupId groupId
     * @param roleId roleId
     * @return group tree.
     */
    private List<UserGroupTree> getSuperUserGroupTree(final Map<Integer, String> groupMap, final Map<Integer, String> roleMap, final int type,
            final int groupId, final int roleId) {
        List<UserGroupTree> rsp = new ArrayList<>();
        List<GroupNode> children = new ArrayList<>();
        for (Entry<Integer, String> entry : groupMap.entrySet()) {
            GroupNode groupNode = getGroupNode(groupMap, roleMap, type, groupId, roleId, entry.getKey());
            children.add(groupNode);
        }
        rsp.add(new UserGroupTree("", children));
        return rsp;
    }

    /**
     * get group tree.
     * @param groupMap groupMap
     * @param roleMap roleMap
     * @param currentGroup currentGroup
     * @param type type
     * @param groupId groupId
     * @param roleId roleId
     * @return group tree list.
     */
    private List<UserGroupTree> getGroupTree(final Map<Integer, String> groupMap, final Map<Integer, String> roleMap,
            final List<Integer> currentGroup, final int type, final int groupId, final int roleId) {
        List<UserGroupTree> rsp = new ArrayList<>();
        List<GroupNode> children = new ArrayList<>();
        for (Integer currGroupId : currentGroup) {
            GroupNode groupNode = getGroupNode(groupMap, roleMap, type, groupId, roleId, currGroupId);
            children.add(groupNode);
        }
        rsp.add(new UserGroupTree("", children));
        return rsp;
    }

    /**
     * get group node.
     * @param groupMap groupMap
     * @param roleMap role map
     * @param type type
     * @param groupId groupId
     * @param roleId  roleId
     * @param currGroupId current group id
     * @return group node
     */
    private GroupNode getGroupNode(final Map<Integer, String> groupMap, final Map<Integer, String> roleMap, final int type, final int groupId,
            final int roleId, final int currGroupId) {
        GroupNode groupNode = new GroupNode(String.valueOf(currGroupId), groupMap.get(currGroupId), this.getRoleNodes(roleMap), "", false);
        if (2 == type && currGroupId == groupId) {
            groupNode.setChecked(true);
            groupNode.setRadio(String.valueOf(roleId));
        }
        return groupNode;
    }

    /**
     * get role tree.
     * @param roleMap roleMap
     * @return List<RoleNode>
     */
    public List<RoleNode> getRoleNodes(final Map<Integer, String> roleMap) {
        List<RoleNode> roleNodes = new ArrayList<>();
        for (Entry<Integer, String> entry : roleMap.entrySet()) {
            RoleNode roleNode = new RoleNode(String.valueOf(entry.getKey()), entry.getValue());
            roleNodes.add(roleNode);
        }
        return roleNodes;
    }

    /**
     * approve user.
     */
    @Override
    @Transactional
    public void approveUser(final UserRequest req) {
        // update status
        userMapper.updateUserStatus("A", req.getUserId());
        // update group and role
        List<UserGroup> groups = req.getGroups();
        for (UserGroup userGroup : groups) {
            UserGroup group = userMapper.findUserGroupByUserIdAndGroupId(userGroup.getUserId(), userGroup.getGroupId());
            if (null != group) {
                userMapper.updateUserGroup(userGroup.getUserId(), userGroup.getGroupId(), userGroup.getRoleId());
            } else {
                userMapper.insertUserGroup(userGroup.getUserId(), userGroup.getGroupId(), userGroup.getRoleId());
            }
        }
    }

    /**
     * reject user.
     */
    @Transactional
    @Override
    public void rejectUser(final Message msg) {
        // add one message
        messageMapper.insert(msg);
        // update user status
        userMapper.updateUserStatus("R", msg.getUserId());
    }
    /**
     * delete user.
     */
    @Override
    @Transactional
    public String delete(final int userId, final int groupId, final int groupLength) {
        // delete user group
        if (0 != groupId) {
            List<Integer> deleteGroup = new ArrayList<>();
            deleteGroup.add(groupId);
            userMapper.deleteUserGroupByUserIdAndGroups(deleteGroup, userId);
        }
        // delete user
        if (1 == groupLength || 0 == groupLength) {
            List<Integer> users = new ArrayList<>();
            users.add(userId);
            userMapper.deleteUsers(users);
        }
        return Constant.SUCCESS_MESSAGE;
    }

    /**
     * juge whether password correct .
     */
    @Override
    public boolean isPasswordSame(final int userId, final String passWord) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        UserDetail user = userMapper.findByUserId(userId);
        if (null != user) {
            return encode.matches(passWord, user.getPassword());
        }
        return false;
    }

    /**
     * update password.
     */
    @Override
    public String updateUserPassword(final int userId, final String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userMapper.updateUserPassword(userId, encoder.encode(password));
        return Constant.SUCCESS_MESSAGE;
    }

}
