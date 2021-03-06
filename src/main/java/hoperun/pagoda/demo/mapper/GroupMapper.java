package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.Group;

/**
 * group mapper.
 * @author zhangxiqin
 *
 */
@Mapper
public interface GroupMapper {

    /**
     * retrieve all group.
     * 
     * @return groups
     */
    List<Group> findAllGroup();

    /**
     * group count.
     * 
     * @return count of group
     */
    int findGroupCount();

    /**
     * select group by name.
     * 
     * @param name group name
     * @return group
     */
    Group findByName(@Param("name") String name);

    /**
     * select group by id.
     * 
     * @param groupId groupId
     * @return Group
     */
    Group findById(@Param("groupId") int groupId);

    /**
     * create group.
     * 
     * @param group group
     * @return 1 if success otherwise 0
     */
    int insert(Group group);

    /**
     * update group.
     * 
     * @param group group
     * @return 1 if success otherwise 0
     */
    int update(Group group);

    /**
     * delete.
     * 
     * @param id id
     */
    void delete(int id);

    /**
     * batch delete.
     * 
     * @param groups groups
     */
    void batchDelete(@Param("groups") List<Integer> groups);

    /**
     * find customer by group id.
     * 
     * @param groupId groupId
     * @return 1 if success otherwise 0
     */
    int findCustomersByGroupId(@Param("groupId") long groupId);

    /**
     * find customer by group ids.
     * 
     * @param groupIds groupIds
     * @return customerList
     */
    List<Integer> findCustomersByGroupIds(@Param("groupIds") List<Integer> groupIds);

    /**
     * update group customer by group id.
     * 
     * @param customers  customers
     * @return 1 if success otherwise 0
     */
    int updateGroupCustomer(@Param("customers") List<Integer> customers);

}
