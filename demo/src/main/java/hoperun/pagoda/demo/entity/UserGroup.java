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
public class UserGroup {
    /**
     * group id
     */
    private String groupId;
    /**
     * role id
     */
    private String roleId;
}
