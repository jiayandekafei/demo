package hoperun.pagoda.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.entity.Role;

/**
 * role service.
 * 
 * @author zhangxiqin
 *
 */
@Service
public interface RoleService {

    /**
     * retrieve role list.
     * 
     * @return role list
     */
    List<Role> findAllRole();

    /**
     * get role map.
     * @return role map
     */
    Map<Integer, String> getRoleMap();

}
