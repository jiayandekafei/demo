package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.bean.CustomerListResponse;
import hoperun.pagoda.demo.entity.Customer;

/**
 * Customer service
 * 
 * @author zhangxiqin
 *
 */
public interface CustomerService {

    /**
     * retrieve Customer list.
     * 
     * @param userId
     * @return
     */
    CustomerListResponse findAll(final int userId, final String superuser, int pageNo, int limit, String name);

    /**
     * retrieve CustomerInfo.
     * 
     * @param CustomerId
     * @return
     */
    Customer findByName(final String name);

    /**
     * retrieve CustomerInfo.
     * 
     * @param CustomerId
     * @return
     */
    Customer findById(final String id);

    /**
     * create new Customer.
     * 
     * @param CustomerId
     * @return
     */
    String create(final Customer request);

    /**
     * create new Customer.
     * 
     * @param CustomerId
     * @return
     */
    String update(final Customer request);

    /**
     * delete by Customer id.
     * 
     * @param CustomerId
     * @return
     */
    void delete(final int CustomerId);

    /**
     * batch.
     * 
     * @param CustomerId
     * @return
     */
    void batchDelete(List<Integer> customers);

    /**
     * get customer by id.
     * 
     * @param customername
     *            customername
     * @return is customer exit
     */
    boolean isCustomerExist(final String customername);

}
