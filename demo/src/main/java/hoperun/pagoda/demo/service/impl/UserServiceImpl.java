package hoperun.pagoda.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.entity.Role;
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
    public UserDetail register(UserDetail userDetail) {
        UserDetail bizUser = userMapper.findByUsername(userDetail.getUsername());
        if (null != bizUser) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User aready exist!"));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDetail.setPassword(encoder.encode(userDetail.getPassword()));
        userMapper.insert(userDetail);
        long roleId = userDetail.getRole().getId();
        Role role = userMapper.findRoleById(roleId);
        userDetail.setRole(role);
        userMapper.insertRole(userDetail.getId(), roleId);
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
