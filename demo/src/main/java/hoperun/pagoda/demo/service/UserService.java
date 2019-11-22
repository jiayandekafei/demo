package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserListResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.entity.Message;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroupTree;

/**
 * User service interface.
 * @author zhangxiqin
 *
 */
public interface UserService {

    /**
     * retrieve user list.
     * @param userId user id
     * @param superuser is super user
     * @param pageNo pageNo 
     * @param limit the number of each page
     * @param name user name
     * @return UserListResponse user List
     */
    UserListResponse findAllUser(int userId, String superuser, int pageNo, int limit, String name);

    /**
     * get user by name.
     * 
     * @param username user name
     * @return UserDetail
     */
    UserDetail findUserByName(String username);

    /**
     * get user by id.
     * 
     * @param userId
     *            user id
     * @return UserDetailResponse user detail
     */
    UserDetailResponse findUserByID(int userId);

    /**
     * juge wether user exist.
     * 
     * @param username
     *            user name
     * @return true if user exist otherwise false
     */
    boolean isUserExist(String username);

    /**
     * get group tree.
     * 
     * @param userId
     *            user id
     * @return UserGroupsResponse
     */
    List<UserGroupTree> getGroupTree(int userId);

    /**
     * add user.
     * 
     * @param user
     *            userRequest
     */
    void insert(UserRequest user);

    /**
     * update user.
     * 
     * @param request
     *            userRequest
     */
    void update(UserRequest request);

    /**
     * approve user.
     * 
     * @param req
     *            user request
     */
    void approveUser(UserRequest req);

    /**
     * reject user.
     * 
     * @param msg
     *            reject message
     */
    void rejectUser(Message msg);

    /**
     * delete user.
     * @param userId user id
     * @param groupId group id
     * @param groupLength group length
     * @return delete message
     */
    String delete(int userId, int groupId, int groupLength);

    /**
     * check password.
     * 
     * @param userId
     *            userId
     * @param passWord
     *            password
     * @return true if same otherwise false
     */
    boolean isPasswordSame(int userId, String passWord);

    /**
     * update user password.
     * 
     * @param userId
     *            userId
     * @param passWord
     *            password
     * @return update message
     */
    String updateUserPassword(int userId, String passWord);

}
