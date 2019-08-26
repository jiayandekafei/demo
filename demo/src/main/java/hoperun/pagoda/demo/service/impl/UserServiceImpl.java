package hoperun.pagoda.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.entity.User;
import hoperun.pagoda.demo.entity.UserDetail;
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
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public UserDetail findUserByName(final String username) {
        UserDetail user = userMapper.findByUsername(username);
        if (null == user) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User dose not exist!"));
        }
        return userMapper.findByUsername(username);
    }

}
