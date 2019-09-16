package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroupTree;

public interface UserService {
    /**
     * register
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

    /**
     * update user by name.
     * 
     * @param UserRequest
     *            userRequest
     */
    void update(final UserRequest request, final int userId);

    /**
     * get user by id.
     * 
     * @param userId
     *            user id
     * @return
     */
    boolean isUserExist(final String username);

    /**
     * get group tree.
     * 
     * @param UserRequest
     *            userRequest
     * @return UserGroupsResponse
     */
    List<UserGroupTree> getGroupTree(final int userId);

    /**
     * get group tree.
     * 
     * @param UserRequest
     *            userRequest
     */
    String delete(final int userId);

    /**
     * get group tree.
     * 
     * @param UserRequest
     *            userRequest
     */
    String insert(final UserRequest user);

    /**
     * update user status.
     * 
     * @param status
     *            status
     * @param userId
     *            userId
     */
    String updateUserStatus(final String status, final int userId);

    String deleteMultiUser(List<Integer> users);
}
