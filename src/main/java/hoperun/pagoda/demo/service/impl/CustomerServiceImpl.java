package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hoperun.pagoda.demo.bean.CustomerListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Customer;
import hoperun.pagoda.demo.mapper.CustomerMapper;
import hoperun.pagoda.demo.mapper.GroupMapper;
import hoperun.pagoda.demo.service.CustomerService;

/**
 * customer service.
 * @author zhangxiqin
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * customer Mapper .
     */
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * group mapper.
     */

    @Autowired
    private GroupMapper groupMapper;

    /**
     * retrieve customer list.
     */
    @Override
    public CustomerListResponse findAll(final String superuser, final int pageNo, final int limit, final String name, final boolean isSelect,
            final List<Integer> currentGroup) {
        List<Customer> customers = customerMapper.findAll();
        if (isSelect) {
            return new CustomerListResponse(customers, customers.size());
        }
        // if super user ,return all customers,otherwise fliter by group id.
        if ("N".equals(superuser)) {
            List<Integer> customerIds = Optional.ofNullable(groupMapper.findCustomersByGroupIds(currentGroup)).orElse(new ArrayList<Integer>());
            customers = customers.stream().filter(customer -> customerIds.contains(customer.getCustomerId())).collect(Collectors.toList());
        }
        // filter by name
        if (!StringUtils.isEmpty(name)) {
            customers = customers.stream().filter(customer -> customer.getCustomername().contains(name)).collect(Collectors.toList());
        }
        int size = customers.size();
        // filter by pageNo and limit
        customers = customers.stream().skip((pageNo - 1) * (long) limit).limit(limit).collect(Collectors.toList());

        return new CustomerListResponse(customers, size);
    }

    /**
     * find customer by name.
     */
    @Override
    public Customer findByName(final String name) {

        return customerMapper.findByName(name);
    }

    /**
     * find customer by id.
     */
    @Override
    public Customer findById(final int id) {
        return customerMapper.findById(id);
    }

    /**
     * add customer.
     */
    @Override
    @Transactional
    public String create(final Customer request) {
        customerMapper.insert(request);
        return Constant.SUCCESS_MESSAGE;
    }

    /**
     * update customer.
     */
    @Override
    @Transactional
    public String update(final Customer request) {
        customerMapper.update(request);
        return Constant.SUCCESS_MESSAGE;
    }

    /**
     * delete customer.
     */
    @Override
    @Transactional
    public void delete(final int customerId) {
        customerMapper.delete(customerId);
        List<Integer> customers = new ArrayList<>();
        customers.add(customerId);
        // update group
        groupMapper.updateGroupCustomer(customers);

    }

    /**
     * delete multi customer.
     */
    @Override
    @Transactional
    public void batchDelete(final List<Integer> customers) {
        customerMapper.batchDelete(customers);
        // update group
        groupMapper.updateGroupCustomer(customers);
    }

    /**
     * juge if customer exist.
     */
    @Override
    public boolean isCustomerExist(final String customername) {
        return null != customerMapper.findByName(customername);
    }
    /**
     * get customer map.
     */
    @Override
    public Map<Integer, String> getCustomerMap() {
        return customerMapper.findAll().stream().collect(Collectors.toMap(Customer::getCustomerId, Customer::getCustomername));
    }
}
