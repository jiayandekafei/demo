package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.entity.GroupNode;
import hoperun.pagoda.demo.entity.Role;
import hoperun.pagoda.demo.entity.RoleNode;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroup;
import hoperun.pagoda.demo.entity.UserGroupTree;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
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
    public UserListResponse findAllUser(final int userId, final String superuser) {
        List<UserDetailResponse> userDetailList = new ArrayList<>();
        List<UserDetail> users = new ArrayList<>();
        // if super user ,return all users
        if ("Y".equals(superuser)) {
            users = userMapper.findAllUser();
        } else {
            List<UserGroup> groups = userMapper.findUserGroups(userId);
            for (UserGroup group : groups) {
                userMapper.findUsersByGroupId(group.getGroup_id());
            }
        }

        // set users
        for (UserDetail user : users) {
            List<UserGroup> groups = userMapper.findUserGroups(user.getUser_id());
            UserDetailResponse userDetail = new UserDetailResponse(user.getUser_id(), user.getUsername(), user.getStatus(), user.getEmail(),
                    user.getJob_title(), user.getSuperuser(), user.getPhoto(), groups);
            userDetailList.add(userDetail);
        }

        return new UserListResponse(userDetailList);
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
            List<UserGroup> groups = userMapper.findUserGroups(user.getUser_id());
            userDetail = new UserDetailResponse(user.getUser_id(), user.getUsername(), user.getStatus(), user.getEmail(), user.getJob_title(),
                    user.getSuperuser(), user.getPhoto(), groups);
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
            userMapper.deleteUserGroup(userId, tempGroups);
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
    public String delete(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String insert(UserRequest user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteMulti(String[] users) {
        // TODO Auto-generated method stub
        return null;
    }

}
