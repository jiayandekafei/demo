package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.Customer;

/**
 * customer mapper.
 * @author zhangxiqin
 *
 */
@Mapper
public interface CustomerMapper {

    /**
     * find all customers.
     * 
     * @return customer list
     */
    List<Customer> findAll();

    /**
     * select customer by name.
     * 
     * @param name customer name
     * @return customer info
     */
    Customer findByName(@Param("name") String name);

    /**
     * select customer by id.
     * 
     * @param id customer id
     * @return customer info
     */
    Customer findById(@Param("id") int id);

    /**
     * insert.
     * 
     * @param customer customer
     */
    void insert(Customer customer);

    /**
     * update.
     * 
     * @param customer customer
     */
    void update(Customer customer);

    /**
     * delete.
     * 
     * @param id customer id
     */
    void delete(int id);

    /**
     * batch delete.
     * 
     * @param customers customers
     */
    void batchDelete(@Param("customers") List<Integer> customers);

}
