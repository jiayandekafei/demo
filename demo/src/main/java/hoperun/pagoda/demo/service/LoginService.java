package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.LoginResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;

public interface LoginService {

    /**
     * sign in
     * 
     * @param username
     * @param password
     * @return
     */
    LoginResponse login(String username, String password);

    /**
     * logout
     * 
     * @param token
     */
    void logout(String token);

    /**
     * refresh Token
     * 
     * @param oldToken
     * @return
     */
    UserDetailResponse refresh(String oldToken);
}
