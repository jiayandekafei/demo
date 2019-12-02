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
import hoperun.pagoda.demo.service.NotesDBService;
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
    private NotesDBService notesDBService;

    /**
    * export DB.
    * @param type 1 if export single DB otherwise 2
    * @param groupId target group id
    * @param dbName target db name(only for single export)
    * @return BaseResponse<String> 
    */
    @SuppressWarnings("unchecked")
    @GetMapping("/export")
    @ApiOperation(value = "export notes DB")
    public BaseResponse<String> exportDB(@RequestParam final int type, @RequestParam final int groupId,
            @RequestParam(required = false) final String dbName) {
        final String method = "exportDB";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list started");
        }

        notesDBService.export(type, groupId, dbName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list end");
        }

        return BaseResponse.ok(Constant.SUCCESS_MESSAGE);
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

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list end");
        }

        return BaseResponse.ok("");
    }
}
