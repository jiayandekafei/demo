package hoperun.pagoda.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.FormInfoRequest;
import hoperun.pagoda.demo.bean.FormInfoResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.mapper.FormInfoMapper;
import hoperun.pagoda.demo.service.FormService;
import hoperun.pagoda.dxlAnalyse.quantity.entity.FormInfo;

/**
 * form service.
 * @author zhangxiqin
 *
 */
@Service
public class FormServiceImpl implements FormService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FormServiceImpl.class);

    /**
     * form info mapper.
     */
    @Autowired
    FormInfoMapper formInfoMapper;

    /**
     * retrieve form info.
     */
    @Override
    public FormInfoResponse retrieveFormInfo(final FormInfoRequest request) {
        String method = "retrieveFormInfo";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve form list started");
        }

        List<FormInfo> formList = formInfoMapper.findAllFormByDB(request.getDbName());
        int size = formList.size();
        // filter by pageNo and limit
        formList = formList.stream().skip((request.getPageNo() - 1) * (long) request.getPageSize()).limit(request.getPageSize())
                .collect(Collectors.toList());
        return new FormInfoResponse(formList, size);
    }

}
