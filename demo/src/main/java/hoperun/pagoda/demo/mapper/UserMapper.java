package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.Role;
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
     * create user role
     * 
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int insertUserRole(@Param("userId") long userId, @Param("groupId") long groupId, @Param("roleId") long roleId);
    /**
     * select role by role id.
     * 
     * @param roleId
     * @return
     */
    Role findRoleById(@Param("roleId") long roleId);

    /**
     * select role by user id.
     * 
     * @param userId
     * @return
     */
    Role findRoleByUserId(@Param("userId") long userId);

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
}
