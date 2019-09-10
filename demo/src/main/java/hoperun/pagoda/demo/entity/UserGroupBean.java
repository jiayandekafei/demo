package hoperun.pagoda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user group.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupBean {
    /**
     * group id
     */
    private int groupId;
    /**
     * group name
     */
    private String groupName;
    /**
     * role id
     */
    private int roleId;
    /**
     * role id
     */
    private String roleName;
}
