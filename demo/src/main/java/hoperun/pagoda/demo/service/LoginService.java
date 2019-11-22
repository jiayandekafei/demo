package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.LoginResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.entity.UserDetail;

/**
 * login service interface.
 * @author zhangxiqin
 *
 */
public interface LoginService {

    /**
     * register.
     * 
     * @param userDetail userDetail
     * @return UserDetail user info
     */
    UserDetail register(UserRegisterRequest userDetail);

    /**
     * sign in.
     * 
     * @param username username
     * @param password password
     * @return login response
     */
    LoginResponse login(String username, String password);

    /**
     * logout.
     * 
     * @param token token
     */
    void logout(String token);

    /**
     * refresh Token.
     * 
     * @param oldToken old token
     * @return user detail response
     */
    UserDetailResponse refresh(String oldToken);
}
