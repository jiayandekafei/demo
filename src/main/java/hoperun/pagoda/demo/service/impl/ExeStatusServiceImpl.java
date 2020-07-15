package hoperun.pagoda.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.ExeStatus;
import hoperun.pagoda.demo.mapper.ExeStatusMapper;
import hoperun.pagoda.demo.service.ExeStatusService;

/**
 * execute status service.
 * @author zhangxiqin
 *
 */
@Service
public class ExeStatusServiceImpl implements ExeStatusService {

    /**
     * execute status mapper.
     */
    @Autowired
    ExeStatusMapper mapper;

    /**
     * update execute status .
     */
    @Override
    public void updateStatus(final ExeStatus exeStatus) {
        // if exist data for current group and type, update otherwise insert
        if (null == mapper.findByTypeAndGroup(exeStatus.getGroupId(), exeStatus.getType())) {
            mapper.insert(exeStatus);
        } else {
            mapper.update(exeStatus);
        }
    }

    /**
     * get exe status.
     */
    @Override
    public ExeStatus findByTypeAndGroup(final int groupId, final int type) {
        return Optional.ofNullable(mapper.findByTypeAndGroup(groupId, type)).orElse(new ExeStatus(0, groupId, type, Constant.EXE_STATUS_C));
    }
}
