package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.Group;

@Mapper
public interface GroupMapper {
    /**
     * create user role
     * 
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int insert(@Param("userId") long userId, @Param("groupId") long groupId, @Param("roleId") long roleId);

    /**
     * retrieve all group.
     * 
     * @return groups
     */
    List<Group> findAllGroup();

    /**
     * select group by id.
     * 
     * @param groupId
     * @return Group
     */
    Group findByGroupId(@Param("groupId") long groupId);
}
