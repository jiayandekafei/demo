package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.AAETransformRequest;
import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.AAETransformService;
import io.swagger.annotations.ApiOperation;

/**
 * Customer Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/aae")
public class AAETransformController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AAETransformController.class);

    /**
     * Customer service.
     */
    @Autowired
    private AAETransformService service;

    /**
     * Get Customer List.
     * @param request AAETransformRequest request
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @GetMapping("/transform")
    @ApiOperation(value = "DB transform")
    public BaseResponse<String> aaeTransform(final AAETransformRequest request) {
        final String method = "AAETransform";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "aae transform started");
        }

        return service.transform(request);
    }
}
