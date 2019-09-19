package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.Group;

@Mapper
public interface GroupMapper {

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

    /**
     * create group
     * 
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int insert(Group group);

    /**
     * select group by id.
     * 
     * @param groupId
     * @return Group
     */
    String findGroupNameById(@Param("groupId") long groupId);

    /**
     * find customer by group id.
     * 
     * @param groupId
     * @return customer id
     */
    int findCustomersByGroupId(@Param("groupId") long groupId);

    /**
     * update group customer by group id.
     * 
     * @param customers
     * @return
     */
    int updateGroupCustomer(@Param("customers") List<Integer> customers);

}
