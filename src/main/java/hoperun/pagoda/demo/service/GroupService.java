package hoperun.pagoda.demo.service;

import java.util.List;
import java.util.Map;

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
     * @param superuser is super user
     * @param pageNo pageNo 
     * @param limit the number of each page
     * @param name group name
     * @param currentGroup currentGroup
     * @return group list
     */
    GroupListResponse findAllGroup(String superuser, int pageNo, int limit, String name, List<Integer> currentGroup);

    /**
     * get group map.
     * @return group map
     */
    Map<Integer, String> getGroupMap();

    /**
     * retrieve groupInfo.
     * 
     * @param groupId groupId
     * @return group
     */
    Group findByGrupId(int groupId);

    /**
     * create new group.
     * 
     * @param request
     *            group
     * @return message
     */
    String create(Group request);

    /**
     * create new group.
     * 
     * @param request request
     * @return message
     */
    String update(Group request);

    /**
     * delete by group id.
     * 
     * @param groupId group id
     */
    void delete(int groupId);

    /**
     * batch.
     * 
     * @param groups groups 
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
