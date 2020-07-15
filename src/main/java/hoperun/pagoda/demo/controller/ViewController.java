package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.ViewInfoRequest;
import hoperun.pagoda.demo.bean.ViewInfoResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.ViewService;
import io.swagger.annotations.ApiOperation;

/**
 * notes DB export Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/view")
public class ViewController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    /**
     * form service.
     */
    @Autowired
    ViewService viewService;

    /**
     * retrieve  view list by DB.
     * @param request  request
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve view list")
    public BaseResponse<ViewInfoResponse> retrieveViewList(final ViewInfoRequest request) {
        final String method = "retrieveViewList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve view List started");
        }
        return BaseResponse.ok(viewService.retrieveViewInfo(request));
    }

}
