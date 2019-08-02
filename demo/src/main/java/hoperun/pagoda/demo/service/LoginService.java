package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.UserResponse;

public interface LoginService {

    /**
     * sign in
     * 
     * @param username
     * @param password
     * @return
     */
    UserResponse login(String username, String password);

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
    UserResponse refresh(String oldToken);
}
