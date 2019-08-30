package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.entity.UserDetail;

public interface UserService {
    /**
     * sign up
     * 
     * @param userDetail
     * @return
     */
    UserDetail register(UserRegisterRequest userDetail);

    /**
     * retrieve user list.
     * 
     * @param userId
     * @return
     */
    UserListResponse findAllUser(int userId, String superuser);

    /**
     * get user by name.
     * 
     * @param userName
     * @return UserDetail
     */
    UserDetail findUserByName(final String username);
    /**
     * get user by id.
     * 
     * @param userId
     *            user id
     * @return
     */
    UserDetailResponse findUserByID(final String userID);

}
