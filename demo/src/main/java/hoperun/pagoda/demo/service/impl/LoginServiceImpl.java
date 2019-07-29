package hoperun.pagoda.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserResponse;
import hoperun.pagoda.demo.entity.User;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.repository.UserRepository;
import hoperun.pagoda.demo.service.LoginService;
import hoperun.pagoda.demo.utils.JwtUtils;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public User register(User user) {
        User bizUser = userRepository.findByUsername(user.getUsername());
        if (null != bizUser) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User aready exist!"));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserResponse login(String username, String password) {
        // User Authentication
        final Authentication authentication = authenticate(username, password);
        // 存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateAccessToken(userDetail);
        // 存储token
        jwtTokenUtil.putToken(username, token);
        return new UserResponse(token, userDetail);
    }

    @Override
    public void logout(String token) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserResponse refresh(String oldToken) {
        // TODO Auto-generated method stub
        return null;
    }

    private Authentication authenticate(String username, String password) {
        try {
            //
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new BusinessException(BaseResponse.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

}
