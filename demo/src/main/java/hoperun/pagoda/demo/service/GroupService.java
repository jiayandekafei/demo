package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.bean.GroupRequest;
import hoperun.pagoda.demo.entity.Group;

/**
 * group service
 * 
 * @author zhangxiqin
 *
 */
public interface GroupService {

    /**
     * retrieve group list.
     * 
     * @param userId
     * @return
     */
    List<Group> findAllGroup();

    /**
     * retrieve groupInfo.
     * 
     * @param groupId
     * @return
     */
    Group findByGrupId(final String groupId);

    /**
     * create new group.
     * 
     * @param groupId
     * @return
     */
    Group create(final GroupRequest request);

    /**
     * create new group.
     * 
     * @param groupId
     * @return
     */
    Group update(final GroupRequest request);

    /**
     * delete by group id.
     * 
     * @param groupId
     * @return
     */
    void delete(final String groupId);

}
