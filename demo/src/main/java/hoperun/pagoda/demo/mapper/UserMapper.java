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
    List<UserGroup> findUsersByGroupId(@Param("groupId") long groupId);

    /**
     * retrieve users under groups.
     * 
     * @param userId
     * @return
     */
    UserGroup findUserGroupByGroupId(@Param("userId") int userId, @Param("groupId") int groupId);

    /**
     * create user role
     * 
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int insertUserRole(@Param("userId") long userId, @Param("groupId") long groupId, @Param("roleId") long roleId);

    /**
     * update user group
     * 
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int updateUserRole(@Param("userId") long userId, @Param("groupId") long groupId, @Param("roleId") long roleId);
}
