package hoperun.pagoda.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.CustomerListResponse;
import hoperun.pagoda.demo.bean.DeleteCustomerRequest;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Customer;
import hoperun.pagoda.demo.service.CustomerService;
import hoperun.pagoda.demo.utils.StringUtils;
import io.swagger.annotations.ApiOperation;

/**
 * Customer Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    /**
     * Customer service.
     */
    @Autowired
    private CustomerService customerService;

    /**
     * Get Customer List.
     * @param superuser  superuser
     * @param pageNo pageNo
     * @param limit  limit
     * @param name name
     * @param isSelect isSelect
     * @param groups groups
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve customer list")
    public BaseResponse<CustomerListResponse> retrieveCustomerList(@RequestParam(required = false) final String superuser,
            @RequestParam(required = false) final int pageNo, @RequestParam(required = false) final int limit,
            @RequestParam(required = false) final String name, @RequestParam final boolean isSelect,
            @RequestParam(required = false) final String groups) {
        final String method = "retrieveCustomerList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list started");
        }

        CustomerListResponse response = customerService.findAll(superuser, pageNo, limit, name, isSelect, StringUtils.convertStringIntList(groups));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list end");
        }

        return BaseResponse.ok(response);
    }

    /**
     * Get Customer List.
     * @param customerId customer id
     * @return BaseResponse<CustomerDetail> Customer reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get Customer by Customer id")
    @GetMapping("/{customerId}")
    public BaseResponse<Customer> getCustomerById(@PathVariable final int customerId) {
        final String method = "getCustomerById";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer by id started");
        }

        return BaseResponse.ok(customerService.findById(customerId));
    }

    /**
     * update Customer List.
     * @param request customer info
     * @return BaseResponse<CustomerDetail> Customer reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "update Customer by Customer id")
    @PutMapping("")
    public BaseResponse<String> updateCustomerById(@RequestBody final Customer request) {
        final String method = "updateCustomerById";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "update Customer by id started");
        }

        return BaseResponse.ok(customerService.update(request));
    }

    /**
     * Add customer.
     * @param request customer info
     * @return customer response
     */
    @SuppressWarnings("unchecked")
    @PostMapping("")
    public BaseResponse<String> addCustomer(@RequestBody final Customer request) {

        return BaseResponse.ok(customerService.create(request));
    }

    /**
     * delete customer.
     * @param customerId customer id
     * @return customer response
     */
    @SuppressWarnings("unchecked")
    @DeleteMapping("/{customerId}")
    public BaseResponse<String> deleteCustomer(@PathVariable("customerId") final Integer customerId) {
        customerService.delete(customerId);
        return BaseResponse.ok(Constant.SUCCESS_MESSAGE);
    }

    /**
     * delte mutil customer.
     * @param request request.
     * @return message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/deleteBatch")
    public BaseResponse<String> batchDeleteCustomer(@RequestBody final DeleteCustomerRequest request) {
        String[] ids = request.getCustomerIds().split(",");
        List<Integer> customers = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            customers.add(Integer.parseInt(ids[i]));
        }
        customerService.batchDelete(customers);
        return BaseResponse.ok(Constant.SUCCESS_MESSAGE);
    }

    /**
     * check .
     * 
     * @param customername customername
     * @return BaseResponse<CustomerDetail> Customer reponse
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "get Customer by Customer id")
    @GetMapping("/checkCustomer/{customername}")
    public BaseResponse<String> isCustomerExist(@PathVariable final String customername) {
        final String method = "isCustomerExist";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer by id started");
        }

        return BaseResponse.ok(customerService.isCustomerExist(customername));
    }

}
