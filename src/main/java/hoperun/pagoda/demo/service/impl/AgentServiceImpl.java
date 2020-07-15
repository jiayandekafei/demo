package hoperun.pagoda.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.AgentInfoRequest;
import hoperun.pagoda.demo.bean.AgentInfoResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.mapper.AgentInfoMapper;
import hoperun.pagoda.demo.service.AgentService;
import hoperun.pagoda.dxlAnalyse.quantity.entity.AgentInfo;

/**
 * agent service.
 * @author zhangxiqin
 *
 */
@Service
public class AgentServiceImpl implements AgentService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentServiceImpl.class);

    /**
     * agent info mapper.
     */
    @Autowired
    AgentInfoMapper agentInfoMapper;

    /**
     * retrieve agent info.
     */
    @Override
    public AgentInfoResponse retrieveAgentInfo(final AgentInfoRequest request) {
        String method = "retrieveViewInfo";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve view list started");
        }

        List<AgentInfo> agentList = agentInfoMapper.findByDB(request.getDbName());
        int size = agentList.size();
        // filter by pageNo and limit
        agentList = agentList.stream().skip((request.getPageNo() - 1) * (long) request.getPageSize()).limit(request.getPageSize())
                .collect(Collectors.toList());
        return new AgentInfoResponse(agentList, size);
    }

}
