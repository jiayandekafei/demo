package hoperun.pagoda.demo.service;

import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.entity.ExeStatus;

/**
 * execute status service.
 * 
 * @author zhangxiqin
 *
 */
@Service
public interface ExeStatusService {

    /**
     * update status.
     * 
     * @param exeStatus exeStatus
     */
    void updateStatus(ExeStatus exeStatus);

    /**
     * get status by groupId and type.
     * 
     * @param groupId groupId
     * @param type type
     * @return Execute status
     */
    ExeStatus findByTypeAndGroup(int groupId, int type);

}
