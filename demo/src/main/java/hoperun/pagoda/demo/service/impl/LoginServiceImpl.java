package hoperun.pagoda.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.LoginResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.service.LoginService;
import hoperun.pagoda.demo.utils.JwtUtils;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public LoginResponse login(String username, String password) {
        // User Authentication
        final Authentication authentication = authenticate(username, password);
        // set authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // gengerate token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateAccessToken(userDetail);
        // set token
        jwtTokenUtil.putToken(username, token);
        return new LoginResponse(token, userDetail.getUser_id(), userDetail.getUsername(), userDetail.getSuperuser());
    }

    @Override
    public void logout(String token) {
        token = token.substring(tokenHead.length() - 1);
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        jwtTokenUtil.deleteToken(userName);

    }

    @Override
    public UserDetailResponse refresh(String oldToken) {
        // TODO Auto-generated method stub
        return null;
    }

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new BusinessException(BaseResponse.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

}
