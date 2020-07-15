package hoperun.pagoda.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.entity.Role;
import hoperun.pagoda.demo.mapper.RoleMapper;
import hoperun.pagoda.demo.service.RoleService;

/**
 * role service.
 * @author zhangxiqin
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * role mapper.
     */
    @Autowired
    private RoleMapper roleMapper;

    /**
     * retrieve all role.
     */
    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }

    /**
     * get Role map.
     */
    @Override
    public Map<Integer, String> getRoleMap() {
        return this.findAllRole().stream().collect(Collectors.toMap(Role::getRoleId, Role::getRoleName));

    }

}
