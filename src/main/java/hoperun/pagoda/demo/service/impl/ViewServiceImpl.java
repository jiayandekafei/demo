package hoperun.pagoda.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.ViewInfoRequest;
import hoperun.pagoda.demo.bean.ViewInfoResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.mapper.ViewInfoMapper;
import hoperun.pagoda.demo.service.ViewService;
import hoperun.pagoda.dxlAnalyse.quantity.entity.ViewInfo;

/**
 * view service.
 * @author zhangxiqin
 *
 */
@Service
public class ViewServiceImpl implements ViewService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewServiceImpl.class);

    /**
     * view info mapper.
     */
    @Autowired
    ViewInfoMapper viewInfoMapper;

    /**
     * retrieve view info.
     */
    @Override
    public ViewInfoResponse retrieveViewInfo(final ViewInfoRequest request) {
        String method = "retrieveViewInfo";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve view list started");
        }

        List<ViewInfo> viewList = viewInfoMapper.findByDB(request.getDbName());
        int size = viewList.size();
        // filter by pageNo and limit
        viewList = viewList.stream().skip((request.getPageNo() - 1) * (long) request.getPageSize()).limit(request.getPageSize())
                .collect(Collectors.toList());
        return new ViewInfoResponse(viewList, size);
    }

}
