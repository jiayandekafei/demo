package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.ExeStatus;
import hoperun.pagoda.demo.service.ExeStatusService;
import io.swagger.annotations.ApiOperation;

/**
 * Execute status Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/exe")
public class ExeStatusController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExeStatusController.class);

    /**
     * Customer service.
     */
    @Autowired
    private ExeStatusService exeStatusService;

    /**
     * Get execute status.
     * @param groupId groupId
     * @param type type
     * @return BaseResponse<ExeStatus> execute status
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/status")
    @ApiOperation(value = "get execute status ")
    public BaseResponse<ExeStatus> getExeStatus(@RequestParam final int groupId, @RequestParam final int type) {
        final String method = "getExeStatus";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get execute status started");
        }

        return BaseResponse.ok(exeStatusService.findByTypeAndGroup(groupId, type));
    }

}
