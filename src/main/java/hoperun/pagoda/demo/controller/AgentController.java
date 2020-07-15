package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.AgentInfoRequest;
import hoperun.pagoda.demo.bean.AgentInfoResponse;
import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.AgentService;
import io.swagger.annotations.ApiOperation;

/**
 * notes DB export Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/agent")
public class AgentController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);

    /**
     * form service.
     */
    @Autowired
    AgentService agentService;

    /**
     * retrieve  view list by DB.
     * @param request  request
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve agent list")
    public BaseResponse<AgentInfoResponse> retrieveViewList(final AgentInfoRequest request) {
        final String method = "retrieveViewList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve agent List started");
        }
        return BaseResponse.ok(agentService.retrieveAgentInfo(request));
    }

}
