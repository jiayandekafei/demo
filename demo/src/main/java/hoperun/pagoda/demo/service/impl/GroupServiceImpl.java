package hoperun.pagoda.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.GroupRequest;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> findAllGroup() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group findByGrupId(String groupId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group create(GroupRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group update(GroupRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String groupId) {
        // TODO Auto-generated method stub

    }

}
