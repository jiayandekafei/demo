package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hoperun.pagoda.demo.bean.GroupListResponse;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.entity.UserGroup;
import hoperun.pagoda.demo.mapper.GroupMapper;
import hoperun.pagoda.demo.mapper.UserMapper;
import hoperun.pagoda.demo.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper GroupMapper;

    @Override
    public GroupListResponse findAllGroup(final int userId, final String superuser, int pageNo, int limit, String name, boolean isGroupTree) {
        List<Group> groups = groupMapper.findAllGroup();
        // if super user ,return all groups,otherwise fliter by group id.
        if ("N".equals(superuser)) {
            List<UserGroup> userGroups = userMapper.findUserGroups(userId);
            for (UserGroup userGroup : userGroups) {
                groups = groups.stream().filter(group -> group.getGroup_id() == userGroup.getGroup_id()).collect(Collectors.toList());
            }
        }
        // filter by name
        if (!StringUtils.isEmpty(name)) {
            groups = groups.stream().filter(group -> name.equals(group.getGroupname())).collect(Collectors.toList());
        }
        int size = groups.size();
        // filter by pageNo and limit
        groups = groups.stream().skip((pageNo - 1) * limit).limit(limit).collect(Collectors.toList());
        setGroupName(groups);
        return new GroupListResponse(groups, size);

    }

    @Override
    public Group findByGrupId(int groupId) {

        return groupMapper.findById(groupId);
    }

    @Override
    @Transactional
    public String create(Group request) {
        groupMapper.insert(request);
        return "successfully!";
    }

    @Override
    @Transactional
    public String update(Group request) {
        groupMapper.insert(request);
        return "successfully!";
    }

    @Override
    @Transactional
    public void delete(int groupId) {
        groupMapper.delete(groupId);
        List<Integer> Groups = new ArrayList<Integer>();
        Groups.add(groupId);
        // update group
        // userMapper.updateUserGroup(groups);

    }

    @Override
    @Transactional
    public void batchDelete(List<Integer> groups) {
        GroupMapper.batchDelete(groups);
        // update group
        // groupMapper.updateUserGroup(groups);
    }

    @Override
    public boolean isGroupExist(String groupname) {
        return null == groupMapper.findByName(groupname) ? false : true;
    }

    /**
     * set Group name.
     *
     * @param groups
     *            groups
     */
    private void setGroupName(List<Group> groups) {
        for (Group group : groups) {
            group.setGroupname(GroupMapper.findById(group.getGroup_id()).getCustomername());
        }
    }
}
