package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.entity.Role;

/**
 * role service
 * 
 * @author zhangxiqin
 *
 */
public interface RoleService {

    /**
     * retrieve role list.
     * 
     * @return role list
     */
    List<Role> findAllRole();

}
