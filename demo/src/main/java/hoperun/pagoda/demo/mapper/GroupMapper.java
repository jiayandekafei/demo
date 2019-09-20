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
     * select customer by name
     * 
     * @param name
     * @return
     */
    Group findByName(@Param("name") String name);

    /**
     * select group by id.
     * 
     * @param groupId
     * @return Group
     */
    Group findById(@Param("groupId") int groupId);

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
     * create group
     * 
     * @param userId
     * @param groupId
     * @param roleId
     * @return
     */
    int update(Group group);

    /**
     * delete.
     * 
     * @param userId
     */
    void delete(int id);

    /**
     * batch delete.
     * 
     * @param userId
     */
    void batchDelete(@Param("groups") List<Integer> groups);

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
