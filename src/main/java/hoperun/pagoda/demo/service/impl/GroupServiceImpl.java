package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hoperun.pagoda.demo.bean.GroupListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.mapper.GroupMapper;
import hoperun.pagoda.demo.mapper.UserMapper;
import hoperun.pagoda.demo.service.CustomerService;
import hoperun.pagoda.demo.service.GroupService;

/**
 * group service.
 * @author zhangxiqin
 *
 */
@Service
public class GroupServiceImpl implements GroupService {

    /**
     * group mapper.
     */
    @Autowired
    private GroupMapper groupMapper;
    /**
     * user mapper.
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * user mapper.
     */
    @Autowired
    private CustomerService customerService;

    /**
     * retrieve all group for List page.
     */
    @Override
    public GroupListResponse findAllGroup(final String superuser, final int pageNo, final int limit, final String name,
            final List<Integer> currentGroup) {

        List<Group> groups = groupMapper.findAllGroup();
        Map<Integer, String> customerMap = customerService.getCustomerMap();

        // if super user ,return all groups,otherwise fliter by group id.
        if ("N".equals(superuser)) {
            groups = groups.stream().filter(group -> currentGroup.contains(group.getGroupId())).collect(Collectors.toList());
        }
        // filter by name
        if (!StringUtils.isEmpty(name)) {
            groups = groups.stream().filter(group -> group.getGroupname().contains(name)).collect(Collectors.toList());
        }
        int size = groups.size();
        // filter by pageNo and limit
        groups = groups.stream().skip((pageNo - 1) * (long) limit).limit(limit).collect(Collectors.toList());
        // set customer name
        groups.forEach(group -> group.setCustomername(customerMap.get(group.getCustomerId())));
        return new GroupListResponse(groups, size);

    }

    /**
     * find group by id. 
     */
    @Override
    public Group findByGrupId(final int groupId) {

        return groupMapper.findById(groupId);
    }

    /**
     * add group.
     */
    @Override
    @Transactional
    public String create(final Group request) {
        groupMapper.insert(request);
        return Constant.SUCCESS_MESSAGE;
    }

    /**
     * update group.
     */
    @Override
    @Transactional
    public String update(final Group request) {
        groupMapper.update(request);
        return Constant.SUCCESS_MESSAGE;
    }

    /**
     * delete group.
     */
    @Override
    @Transactional
    public void delete(final int groupId) {
        groupMapper.delete(groupId);
        List<Integer> groups = new ArrayList<>();
        groups.add(groupId);
        // update user group role
        userMapper.deleteUserGroupByGroupIds(groups);

    }

    /**
     * delete mutil group.
     */
    @Override
    @Transactional
    public void batchDelete(final List<Integer> groups) {
        groupMapper.batchDelete(groups);
        // update group
        userMapper.deleteUserGroupByGroupIds(groups);
    }

    /**
     * juge if group exist.
     */
    @Override
    public boolean isGroupExist(final String groupname) {
        return null != groupMapper.findByName(groupname);
    }

    /**
     * get group map.
     */
    @Override
    public Map<Integer, String> getGroupMap() {
        return groupMapper.findAllGroup().stream().collect(Collectors.toMap(Group::getGroupId, Group::getGroupname));
    }

}
