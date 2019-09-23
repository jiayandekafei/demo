package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.entity.UserGroup;

@Mapper
public interface UserMapper {
    /**
     * select user by name
     * 
     * @param name
     * @return
     */
    UserDetail findByUsername(@Param("username") String username);

    /**
     * select user by name
     * 
     * @param name
     * @return
     */
    UserDetail findByUserId(@Param("userId") String userId);

    /**
     * insert.
     * 
     * @param userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * update.
     * 
     * @param userDetail
     */
    void update(UserRequest userRequest);

    /**
     * delete.
     * 
     * @param userId
     */
    void deleteUsers(@Param("users") List<Integer> users);

    /**
     * delete.
     * 
     * @param userId
     */
    void delete(int userId);

    /**
     * select role by user id.
     * 
     * @param userId
     * @return
     */
    List<UserDetail> findAllUser();

    /**
     * select role by user id.
     * 
     * @param userId
     * @return
     */
    List<UserGroup> findUserGroups(@Param("userId") long userId);

    /**
     * retrieve users under groups.
     * 
     * @param userId
     * @return
     */
    List<Integer> findUsersByGroupId(@Param("group_id") long group_id);

    /**
     * retrieve users under groups.
     * 
     * @param userId
     * @return
     */
    UserGroup findUserGroupByGroupId(@Param("userId") int userId, @Param("group_id") int group_id);
    /**
     * retrieve users under groups.
     * 
     * @param userId
     * @return
     */
    List<Integer> findUserGroupByGroupIds(@Param("userId") int userId, @Param("groupIds") List<Integer> groupIds);

    /**
     * create user role.
     *
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int insertUserGroup(@Param("userId") long userId, @Param("group_id") long group_id, @Param("role_id") long role_id);

    /**
     * update user group.
     *
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int updateUserGroup(@Param("userId") long userId, @Param("group_id") long group_id, @Param("role_id") long role_id);

    /**
     * delete user role.
     *
     * @param userId
     * @param int
     *            []Group ID
     * @return
     */
    int deleteUserGroupByUserIdAndGroups(@Param("userId") int userId, @Param("groups") List<Integer> groups);

    /**
     * delete user role.
     *
     * @param userId
     * @return
     */
    int deleteUserGroupByUserIds(@Param("users") List<Integer> users);

    /**
     * update user status.
     *
     * @param status
     *            status
     * @param userId
     *            userId
     * 
     * @return
     */
    int updateUserStatus(@Param("status") String status, @Param("userId") int userId);
}
