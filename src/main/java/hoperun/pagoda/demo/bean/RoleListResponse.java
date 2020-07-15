package hoperun.pagoda.demo.bean;

import java.util.List;

import hoperun.pagoda.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User response.
 *
 * @author zhangxiqin
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleListResponse {
    /**
     * role list.
     */
    private List<Role> roles;

}
