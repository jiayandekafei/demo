package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.UserResponse;
import hoperun.pagoda.demo.entity.User;

public interface LoginService {
    /**
     * sign up
     * 
     * @param userDetail
     * @return
     */
    User register(User userDetail);

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
