package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.entity.GroupUserNum;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroup;

/**
 * user mapper.
 * @author zhangxiqin
 *
 */
@Mapper
public interface UserMapper {
    /**
     * select user by name.
     * 
     * @param username username 
     * @return UserDetail user info
     */
    UserDetail findByUsername(@Param("username") String username);

    /**
     * user count.
     * 
     * @param status status
     * @return user count
     */
    int findUserCount(@Param("status") String status);

    /**
     * select user by id.
     * 
     * @param userId user id 
     * @return UserDetail user info
     */
    UserDetail findByUserId(@Param("userId") int userId);

    /**
     * insert.
     * 
     * @param userDetail userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * update.
     * 
     * @param userRequest userRequest
     */
    void update(UserRequest userRequest);

    /**
     * delete.
     * 
     * @param users users
     */
    void deleteUsers(@Param("users") List<Integer> users);

    /**
     * retrieve all users.
     * 
     * @param superuser superuser
     * @return user list
     */
    List<UserDetail> findAllUser(@Param("superuser") String superuser);

    /**
     * retrieve all users.
     * 
     * @return user list
     */
    List<UserDetail> findApproveUser();

    /**
     * retrieve user group.
     * 
     * @param userId user id
     * @return user group list
     */
    List<UserGroup> findUserGroups(@Param("userId") long userId);

    /**
     * retrieve users under current group.
     * 
     * @param groupId group id.
     * @return user id list
     */
    List<Integer> findUsersByGroupId(@Param("groupId") long groupId);

    /**
     * retrieve user's group info.
     * 
     * @param userId user id
     * @param groupId group id
     * @return user Group
     */
    UserGroup findUserGroupByUserIdAndGroupId(@Param("userId") int userId, @Param("groupId") int groupId);
    /**
     * retrieve user's other group .
     * 
     * @param userId userId
     * @param groupIds group id
     * @return group id list
     */
    List<Integer> findUserGroupByGroupIds(@Param("userId") int userId, @Param("groupIds") List<Integer> groupIds);

    /**
     * create user group .
     *
     * @param userId userId
     * @param groupId groupId
     * @param roleId roleId
     * @return 1 if created successful otherwise 0
     */
    int insertUserGroup(@Param("userId") long userId, @Param("groupId") long groupId, @Param("roleId") long roleId, @Param("status") String status);

    /**
     * update user group.
     *
     * @param userId userId
     * @param groupId groupId
     * @param roleId roleId
     * @return  1 if updated successful otherwise 0
     */
    int updateUserGroup(@Param("userId") long userId, @Param("groupId") long groupId, @Param("roleId") long roleId, @Param("status") String status);

    /**
     * delete user role.
     *
     * @param deleteGroup group list
     * @param userId userId
     * @return  1 if updated successful otherwise 0
     */
    int deleteUserGroupByUserIdAndGroups(@Param("deleteGroup") List<Integer> deleteGroup, @Param("userId") int userId);

    /**
     * delete user role by users.
     *
     * @param users users
     * @return 1 if updated successful otherwise 0
     */
    int deleteUserGroupByUserIds(@Param("users") List<Integer> users);
    /**
     * delete user group and role by groups .
     *
     * @param groups groups
     * @return 1 if updated successful otherwise 0
     */
    int deleteUserGroupByGroupIds(@Param("groups") List<Integer> groups);

    /**
     * update user status.
     *
     * @param status
     *            status
     * @param userId
     *            userId
     * 
     * @return 1 if updated successful otherwise 0
     */
    int updateUserStatus(@Param("status") String status, @Param("userId") int userId);
    /**
     * update user status.
     *
     * @param status
     *            status
     * @param userId
     *            userId
     * 
     * @return 1 if updated successful otherwise 0
     */
    int updateUserGroupStatus(@Param("status") String status, @Param("userId") int userId, @Param("groupId") int groupId);

    /**
     * update user password.
     *
     * @param userId
     *            userId
     * @param password
     *            password
     * 
     * @return 1 if updated successful otherwise 0
     */
    int updateUserPassword(@Param("userId") int userId, @Param("password") String password);

    /**
     * retrieveUserNumber.
     * @return List<GroupUserNum> user numbers for each group
     */
    List<GroupUserNum> retrieveUserNumber();

    /**
     * find super  user.
     * @return List<String> username of super user
     */
    List<String> findSuperuser();

    /**
     * find PM.
     * 
     * @param groupId groupId
     * @return List<String> userId of super user
     */
    List<String> findPMByGroup(@Param("groupId") int groupId);

}
