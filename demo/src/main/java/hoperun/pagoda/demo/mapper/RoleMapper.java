package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.demo.entity.Role;

/**
 * role mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface RoleMapper {
    /**
     * retrieve all group.
     * 
     * @return groups
     */
    List<Role> findAllRole();
}
