package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroup;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.mapper.UserMapper;
import hoperun.pagoda.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
        UserDetail userDetail = new UserDetail(request.getUsername(), encoder.encode(request.getPassword()), request.getEamil(),
                request.getJobTitle(), request.getGroupId(), "");
        // insert user bace info
        userMapper.insert(userDetail);
        // update user group role table
        if (!StringUtils.isEmpty(request.getGroupId())) {

            userMapper.insertUserRole(userDetail.getUser_id(), request.getGroupId(), 0);
        }

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
                userMapper.findUsersByGroupId(group.getGroupId());
            }
        }

        // set users
        for (UserDetail user : users) {
            List<UserGroup> groups = userMapper.findUserGroups(user.getUser_id());
            UserDetailResponse userDetail = new UserDetailResponse(user.getUser_id(), user.getUsername(), user.getStatus(), user.getEamil(),
                    user.getJobTitle(), user.getSuperuser(), user.getPhoto(), groups);
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
            userDetail = new UserDetailResponse(user.getUser_id(), user.getUsername(), user.getStatus(), user.getEamil(), user.getJobTitle(),
                    user.getSuperuser(), user.getPhoto(), groups);
        } else {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User dose not exist!"));
        }
        return userDetail;
    }

}
