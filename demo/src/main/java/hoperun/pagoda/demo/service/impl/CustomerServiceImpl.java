package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hoperun.pagoda.demo.bean.CustomerListResponse;
import hoperun.pagoda.demo.entity.Customer;
import hoperun.pagoda.demo.entity.UserGroup;
import hoperun.pagoda.demo.mapper.CustomerMapper;
import hoperun.pagoda.demo.mapper.GroupMapper;
import hoperun.pagoda.demo.mapper.UserMapper;
import hoperun.pagoda.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public CustomerListResponse findAll(final int userId, final String superuser, int pageNo, int limit, String name, boolean isSelect) {
        List<Customer> customers = customerMapper.findAll();
        if (isSelect) {
            return new CustomerListResponse(customers, customers.size());
        }
        // if super user ,return all customers,otherwise fliter by group id.
        if ("N".equals(superuser)) {
            List<UserGroup> groups = userMapper.findUserGroups(userId);
            for (UserGroup group : groups) {
                int customerId = groupMapper.findCustomersByGroupId(group.getGroup_id());
                customers = customers.stream().filter(customer -> customer.getCustomer_id() == customerId).collect(Collectors.toList());
            }
        }
        // filter by name
        if (!StringUtils.isEmpty(name)) {
            customers = customers.stream().filter(customer -> name.equals(customer.getCustomername())).collect(Collectors.toList());
        }
        int size = customers.size();
        // filter by pageNo and limit
        customers = customers.stream().skip((pageNo - 1) * limit).limit(limit).collect(Collectors.toList());

        return new CustomerListResponse(customers, size);
    }

    @Override
    public Customer findByName(String name) {

        return customerMapper.findByName(name);
    }

    @Override
    public Customer findById(int id) {
        return customerMapper.findById(id);
    }

    @Override
    @Transactional
    public String create(Customer request) {
        customerMapper.insert(request);
        return "successfully!";
    }

    @Override
    @Transactional
    public String update(Customer request) {
        customerMapper.update(request);
        return "successfully!";
    }

    @Override
    @Transactional
    public void delete(int customerId) {
        customerMapper.delete(customerId);
        List<Integer> customers = new ArrayList<Integer>();
        customers.add(customerId);
        // update group
        groupMapper.updateGroupCustomer(customers);

    }

    @Override
    @Transactional
    public void batchDelete(List<Integer> customers) {
        customerMapper.batchDelete(customers);
        // update group
        groupMapper.updateGroupCustomer(customers);
    }

    @Override
    public boolean isCustomerExist(String customername) {
        return null == customerMapper.findByName(customername) ? false : true;
    }

}
