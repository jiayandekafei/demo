package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.bean.GroupListResponse;
import hoperun.pagoda.demo.entity.Group;

/**
 * group service.
 * 
 * @author zhangxiqin
 *
 */
public interface GroupService {

    /**
     * retrieve group list.
     * 
     * @param userId user id
     * @param superuser is super user
     * @param pageNo pageNo 
     * @param limit the number of each page
     * @param name group name
     * @param isGroupTree whether get for group tree
     * @return group list
     */
    GroupListResponse findAllGroup(int userId, String superuser, int pageNo, int limit, String name, boolean isGroupTree);

    /**
     * retrieve groupInfo.
     * 
     * @param groupId
     * @return group
     */
    Group findByGrupId(int groupId);

    /**
     * create new group.
     * 
     * @param request
     *            group
     * @return
     */
    String create(Group request);

    /**
     * create new group.
     * 
     * @param request
     * @return
     */
    String update(Group request);

    /**
     * delete by group id.
     * 
     * @param groupId
     * @return
     */
    void delete(int groupId);

    /**
     * batch.
     * 
     * @param groups
     * @return
     */
    void batchDelete(List<Integer> groups);

    /**
     * check if the group already exist.
     * 
     * @param grouprname
     *            grouprname
     * @return is group exit
     */
    boolean isGroupExist(String grouprname);

}
