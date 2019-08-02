package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.Role;
import hoperun.pagoda.demo.entity.User;
import hoperun.pagoda.demo.entity.UserDetail;

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
     * insert.
     * 
     * @param userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * create user role
     * 
     * @param userId
     * @param roleId
     * @return
     */
    int insertRole(@Param("userId") long userId, @Param("roleId") long roleId);

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
    List<User> findAllUser();
}
