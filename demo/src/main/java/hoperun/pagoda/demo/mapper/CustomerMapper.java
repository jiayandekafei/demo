package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.entity.Customer;

@Mapper
public interface CustomerMapper {

    /**
     * select role by user id.
     * 
     * @param userId
     * @return
     */
    List<Customer> findAll();

    /**
     * select customer by name
     * 
     * @param name
     * @return
     */
    Customer findByName(@Param("name") String name);

    /**
     * select customer by id
     * 
     * @param name
     * @return
     */
    Customer findById(@Param("id") String id);

    /**
     * insert.
     * 
     * @param Customer
     */
    void insert(Customer Customer);

    /**
     * update.
     * 
     * @param Customer
     */
    void update(UserRequest userRequest);

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
    void batchDelete(@Param("customers") List<Integer> customers);

}
