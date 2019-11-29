package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.CustomerListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.CustomerService;
import hoperun.pagoda.demo.utils.StringUtils;
import io.swagger.annotations.ApiOperation;

/**
 * notes DB export Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/notes/db")
public class NotesDBController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesDBController.class);

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
    @GetMapping("/export")
    @ApiOperation(value = "retrieve customer list")
    public BaseResponse<CustomerListResponse> exportDB() {
        final String method = "retrieveCustomerList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list started");
        }

        CustomerListResponse response = customerService.findAll();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list end");
        }

        return BaseResponse.ok(response);
    }

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
}
