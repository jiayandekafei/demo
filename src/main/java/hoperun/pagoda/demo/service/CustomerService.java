package hoperun.pagoda.demo.service;

import java.util.List;
import java.util.Map;

import hoperun.pagoda.demo.bean.CustomerListResponse;
import hoperun.pagoda.demo.entity.Customer;

/**
 * Customer service.
 * 
 * @author zhangxiqin
 *
 */
public interface CustomerService {

    /**
     * retrieve customer info.
     * @param superuser if superuser .
     * @param pageNo pageNo
     * @param limit display number for each page.
     * @param name customer name .
     * @param isSelect if for group add page.
     * @param currentGroup current groups
     * @return customer list.
     */
    CustomerListResponse findAll(String superuser, int pageNo, int limit, String name, boolean isSelect, List<Integer> currentGroup);

    /**
     * retrieve CustomerInfo by name.
     * 
     * @param name  custoemr name 
     * @return customer info
     */
    Customer findByName(String name);

    /**
     * retrieve CustomerInfo by id.
     * 
     * @param id CustomerId
     * @return customerd info
     */
    Customer findById(int id);

    /**
     * create new Customer.
     * 
     * @param request  request
     * @return message
     */
    String create(Customer request);

    /**
     * create new Customer.
     * 
     * @param request request
     * @return message
     */
    String update(Customer request);

    /**
     * delete by Customer id.
     * 
     * @param customerId  customerId
     */
    void delete(int customerId);

    /**
     * batch.
     * 
     * @param customers CustomerId
     */
    void batchDelete(List<Integer> customers);

    /**
     * get customer by id.
     * 
     * @param customername
     *            customername
     * @return is customer exit
     */
    boolean isCustomerExist(String customername);

    /**
     * get custoemr service.
     * @return customer service map.
     */
    Map<Integer, String> getCustomerMap();

}
