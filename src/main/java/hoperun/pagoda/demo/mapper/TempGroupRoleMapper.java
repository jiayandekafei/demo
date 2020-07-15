package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.UserGroup;

/**
 * temp group role  mapper.
 * @author zhangxiqin
 *
 */
@Mapper
public interface TempGroupRoleMapper {

    /**
     * retrieve user group.
     * 
     * @param userId user id
     * @return user group list
     */
    List<UserGroup> findUserGroups(@Param("userId") long userId);

    /**
     * insert.
     * 
     * @param userGroup userGroup
     */
    void insert(UserGroup userGroup);

    /**
     * update.
     * 
     * @param userGroup userGroup
     */
    void update(UserGroup userGroup);

    /**
     * delete.
     * 
     * @param userId userId
     * 
     * @return deleted record;
     */
    int delete(int userId);

    /**
     * delete.
     * 
     * @param userId userId
     * @param groupId groupId
     * 
     * @return deleted record;
     */
    int deleteByUserIdAndGroupId(int userId, int groupId);
}
