package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.AgentInfoRequest;
import hoperun.pagoda.demo.bean.AgentInfoResponse;

/**
 * agent service.
 * 
 * @author zhangxiqin
 *
 */
public interface AgentService {

    /**
     * retrieve agent List.
     * @param request request
     * @return  ViewInfoResponse
     */
    AgentInfoResponse retrieveAgentInfo(AgentInfoRequest request);

}
