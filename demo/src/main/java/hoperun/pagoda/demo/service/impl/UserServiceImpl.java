package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.entity.GroupNode;
import hoperun.pagoda.demo.entity.Message;
import hoperun.pagoda.demo.entity.Role;
import hoperun.pagoda.demo.entity.RoleNode;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroup;
import hoperun.pagoda.demo.entity.UserGroupBean;
import hoperun.pagoda.demo.entity.UserGroupTree;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.mapper.GroupMapper;
import hoperun.pagoda.demo.mapper.MessageMapper;
import hoperun.pagoda.demo.mapper.RoleMapper;
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
     * groupMapper.
     */
    @Autowired
    private GroupMapper groupMapper;
    /**
     * roleMapper.
     */
    @Autowired
    private RoleMapper roleMapper;

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
        if (!Collections.isEmpty(userGroups)) {
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
    }

    /**
     * retrieve all user.
     */
    @Override
    public UserListResponse findAllUser(final int userId, final String superuser, final int pageNo, final int limit, final String name) {
        List<UserDetailResponse> userDetailList = new ArrayList<>();
        List<UserDetail> users = userMapper.findAllUser();
        // if super user ,return all users,otherwise fliter by group id.
        if ("N".equals(superuser)) {
            List<UserGroup> groups = userMapper.findUserGroups(userId);
            for (UserGroup group : groups) {
                users = users.stream().filter(user -> userMapper.findUsersByGroupId(group.getGroupId()).contains(user.getUserId()))
                        .collect(Collectors.toList());
            }
        }
        // filter by name
        if (!StringUtils.isEmpty(name)) {
            users = users.stream().filter(user -> name.equals(user.getUsername())).collect(Collectors.toList());
        }
        int size = users.size();
        // filter by pageNo and limit
        users = users.stream().skip((pageNo - 1) * (long) limit).limit(limit).collect(Collectors.toList());
        // set users
        for (UserDetail user : users) {
            List<UserGroupTree> group = this.getGroupTree(user.getUserId());
            UserDetailResponse userDetail = new UserDetailResponse(user.getUserId(), user.getUsername(), user.getStatus(), user.getEmail(),
                    user.getJobTitle(), user.getSuperuser(), user.getPhoto(), this.getUserGroups(user.getUserId()), group);
            userDetailList.add(userDetail);
        }

        return new UserListResponse(userDetailList, size);
    }

    /**
     * get user group.
     * @param userId user id
     * @return  user group list
     */
    public List<UserGroupBean> getUserGroups(final int userId) {
        List<UserGroupBean> userGroups = new ArrayList<>();
        List<UserGroup> group = userMapper.findUserGroups(userId);
        for (UserGroup userGroup : group) {
            String groupName = groupMapper.findById(userGroup.getGroupId()).getGroupname();
            String roleName = roleMapper.findRoleNameById(userGroup.getRoleId());
            userGroups.add(new UserGroupBean(userGroup.getGroupId(), groupName, userGroup.getRoleId(), roleName));
        }
        return userGroups;
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
            List<UserGroupTree> group = this.getGroupTree(user.getUserId());
            userDetail = new UserDetailResponse(user.getUserId(), user.getUsername(), user.getStatus(), user.getEmail(), user.getJobTitle(),
                    user.getSuperuser(), user.getPhoto(), this.getUserGroups(user.getUserId()), group);
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
    public List<UserGroupTree> getGroupTree(final int userId) {
        List<Group> groups = groupService.findAllGroup(userId, null, 0, 0, null, true).getGroups();
        List<UserGroupTree> rsp = new ArrayList<>();
        List<GroupNode> children = new ArrayList<>();
        if (!Collections.isEmpty(groups)) {
            for (Group group : groups) {
                GroupNode groupNode = new GroupNode(String.valueOf(group.getGroupId()), group.getGroupname(), this.getRoleNodes(), "", false);
                setGroupRole(userId, groupNode);
                children.add(groupNode);
            }
        }
        rsp.add(new UserGroupTree("", children));
        return rsp;
    }

    /**
     * set group role.
     * @param userId user id
     * @param groupNode group 
     */
    public void setGroupRole(final int userId, final GroupNode groupNode) {
        List<UserGroup> userGroups = userMapper.findUserGroups(userId);
        Map<String, String> map = new HashMap<>();
        if (!Collections.isEmpty(userGroups)) {
            for (UserGroup group : userGroups) {
                map.put(String.valueOf(userId) + String.valueOf(group.getGroupId()), String.valueOf(group.getRoleId()));

            }
        }
        String key = String.valueOf(userId) + groupNode.getId();
        if (map.containsKey(key)) {
            groupNode.setChecked(true);
            groupNode.setRadio(map.get(key));
        }
    }

    /**
     * get role tree.
     * 
     * @return List<RoleNode>
     */
    public List<RoleNode> getRoleNodes() {
        List<RoleNode> roleNodes = new ArrayList<>();
        List<Role> roles = roleService.findAllRole();
        if (!Collections.isEmpty(roles)) {
            for (Role role : roles) {
                RoleNode roleNode = new RoleNode(String.valueOf(role.getRoleId()), role.getRoleName());
                roleNodes.add(roleNode);
            }
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
    @Override
    public void rejectUser(final Message msg) {
        // add one message
        messageMapper.insert(msg);
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
     * juge wether password correct .
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
