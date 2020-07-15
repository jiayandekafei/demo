package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.FormInfoRequest;
import hoperun.pagoda.demo.bean.FormInfoResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.FormService;
import io.swagger.annotations.ApiOperation;

/**
 * notes DB export Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/form")
public class FormController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FormController.class);

    /**
     * form service.
     */
    @Autowired
    FormService formService;

    /**
     * retrieve form list by DB.
     * @param request  request
     * @return BaseResponse<FormInfoResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve DB list")
    public BaseResponse<FormInfoResponse> retrieveFormList(final FormInfoRequest request) {
        final String method = "retrieveFormList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve form List started");
        }
        return BaseResponse.ok(formService.retrieveFormInfo(request));
    }

}
