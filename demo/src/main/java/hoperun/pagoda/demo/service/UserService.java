package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.entity.User;
import hoperun.pagoda.demo.entity.UserDetail;

public interface UserService {
    /**
     * sign up
     * 
     * @param userDetail
     * @return
     */
    UserDetail register(UserDetail userDetail);

    /**
     * retrieve user list.
     * 
     * @param userId
     * @return
     */
    List<User> findAllUser();

    /**
     * get user by name.
     * 
     * @param userId
     * @return
     */
    UserDetail findUserByName(final String username);
}
