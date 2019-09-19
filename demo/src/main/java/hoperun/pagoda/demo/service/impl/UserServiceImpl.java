package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.constant.StatusCode;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.entity.GroupNode;
import hoperun.pagoda.demo.entity.Role;
import hoperun.pagoda.demo.entity.RoleNode;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroup;
import hoperun.pagoda.demo.entity.UserGroupBean;
import hoperun.pagoda.demo.entity.UserGroupTree;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.mapper.GroupMapper;
import hoperun.pagoda.demo.mapper.RoleMapper;
import hoperun.pagoda.demo.mapper.UserMapper;
import hoperun.pagoda.demo.service.GroupService;
import hoperun.pagoda.demo.service.RoleService;
import hoperun.pagoda.demo.service.UserService;
import io.jsonwebtoken.lang.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RoleService roleService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    @Transactional
    public UserDetail register(UserRegisterRequest request) {
        UserDetail bizUser = userMapper.findByUsername(request.getUsername());
        if (null != bizUser) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User aready exist!"));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetail userDetail = new UserDetail(request.getUsername(), encoder.encode(request.getPassword()), request.getEmail(),
                request.getJob_title());
        // insert user bace info
        userMapper.insert(userDetail);

        return userDetail;
    }

    @Override
    public UserListResponse findAllUser(final int userId, final String superuser, int pageNo, int limit, String name) {
        List<UserDetailResponse> userDetailList = new ArrayList<>();
        List<UserDetail> users = userMapper.findAllUser();
        // if super user ,return all users,otherwise fliter by group id.
        if ("N".equals(superuser)) {
            List<UserGroup> groups = userMapper.findUserGroups(userId);
            for (UserGroup group : groups) {
                // userIds = userMapper.findUsersByGroupId(group.getGroup_id());
                users = users.stream().filter(user -> userMapper.findUsersByGroupId(group.getGroup_id()).contains(user.getUser_id()))
                        .collect(Collectors.toList());
            }
        }
        // filter by name
        if (!StringUtils.isEmpty(name)) {
            users = users.stream().filter(user -> name.equals(user.getUsername())).collect(Collectors.toList());
        }
        int size = users.size();
        // filter by pageNo and limit
        users = users.stream().skip((pageNo - 1) * limit).limit(limit).collect(Collectors.toList());
        // set users
        for (UserDetail user : users) {
            List<UserGroupTree> group = this.getGroupTree(user.getUser_id());
            UserDetailResponse userDetail = new UserDetailResponse(user.getUser_id(), user.getUsername(), StatusCode.W.getMsg(), user.getEmail(),
                    user.getJob_title(), user.getSuperuser(), user.getPhoto(), this.getUserGroups(user.getUser_id()), group);
            userDetailList.add(userDetail);
        }

        return new UserListResponse(userDetailList, size);
    }

    public List<UserGroupBean> getUserGroups(final int userId) {
        List<UserGroupBean> userGroups = new ArrayList<UserGroupBean>();
        List<UserGroup> group = userMapper.findUserGroups(userId);
        for (UserGroup userGroup : group) {
            String groupName = groupMapper.findGroupNameById(userGroup.getGroup_id());
            String roleName = roleMapper.findRoleNameById(userGroup.getRole_id());
            userGroups.add(new UserGroupBean(userGroup.getGroup_id(), groupName, userGroup.getRole_id(), roleName));
        }

        return userGroups;

    }

    @Override
    public UserDetail findUserByName(final String username) {
        UserDetail user = userMapper.findByUsername(username);
        if (null == user) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User dose not exist!"));
        }
        return userMapper.findByUsername(username);
    }

    @Override
    public UserDetailResponse findUserByID(String userId) {
        UserDetailResponse userDetail;
        UserDetail user = userMapper.findByUserId(userId);
        if (null != user) {
            List<UserGroupTree> group = this.getGroupTree(user.getUser_id());
            userDetail = new UserDetailResponse(user.getUser_id(), user.getUsername(), user.getStatus(), user.getEmail(), user.getJob_title(),
                    user.getSuperuser(), user.getPhoto(), this.getUserGroups(user.getUser_id()), group);
        } else {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User dose not exist!"));
        }
        return userDetail;
    }

    @Override
    public boolean isUserExist(String username) {
        UserDetail user = userMapper.findByUsername(username);

        return null == user ? false : true;
    }

    @Override
    @Transactional
    public void update(final UserRequest request, final int userId) {
        userMapper.update(request);
        List<UserGroup> groups = request.getGroups();
        List<Integer> groupIds = new ArrayList<>();
        // update user group role table
        if (!Collections.isEmpty(groups)) {
            for (UserGroup userGroup : groups) {
                // check weather the group exist
                UserGroup group = userMapper.findUserGroupByGroupId(userId, userGroup.getGroup_id());
                if (null == group) {
                    userMapper.insertUserGroup(userId, userGroup.getGroup_id(), userGroup.getRole_id());
                } else {
                    userMapper.updateUserGroup(userId, userGroup.getGroup_id(), userGroup.getRole_id());
                }
                groupIds.add(userGroup.getGroup_id());
            }
            // check if has other groups
            List<Integer> tempGroups = userMapper.findUserGroupByGroupIds(userId, groupIds);
            // delete group
            userMapper.deleteUserGroupByUserIdAndGroups(userId, tempGroups);
        }
    }

    @Override
    public List<UserGroupTree> getGroupTree(final int userId) {
        List<Group> groups = groupService.findAllGroup();
        List<UserGroupTree> rsp = new ArrayList<>();
        List<GroupNode> children = new ArrayList<>();
        if (!Collections.isEmpty(groups)) {
            for (Group group : groups) {
                GroupNode groupNode = new GroupNode(String.valueOf(group.getGroup_id()), group.getGroupname(), this.getRoleNodes(), "", false);
                setGroupRole(userId, groupNode);
                children.add(groupNode);
            }
        }
        rsp.add(new UserGroupTree("", children));
        return rsp;
    }

    /**
     * 
     * @param userId
     * @param groupNode
     */
    public void setGroupRole(final int userId, final GroupNode groupNode) {
        List<UserGroup> userGroups = userMapper.findUserGroups(userId);
        Map<String, String> map = new HashMap<String, String>();
        if (!Collections.isEmpty(userGroups)) {
            for (UserGroup group : userGroups) {
                map.put(String.valueOf(userId) + String.valueOf(group.getGroup_id()), String.valueOf(group.getRole_id()));

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
                RoleNode roleNode = new RoleNode(String.valueOf(role.getRole_id()), role.getRole());
                roleNodes.add(roleNode);
            }

        }
        return roleNodes;
    }

    @Override
    @Transactional
    public String delete(final int userId) {
        userMapper.delete(userId);
        List<Integer> users = new ArrayList<>();
        users.add(userId);
        userMapper.deleteUserGroupByUserIds(users);
        return "successfully!";
    }

    @Override
    public String insert(UserRequest user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public String deleteMultiUser(List<Integer> users) {
        userMapper.deleteUsers(users);
        userMapper.deleteUserGroupByUserIds(users);
        return "successfully!";
    }

    @Override
    public String updateUserStatus(String status, int userId) {
        userMapper.updateUserStatus(status, userId);
        return "successfully!";
    }

}
