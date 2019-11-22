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
     * user id.
     */
    private int userId;
    /**
     * group id.
     */
    private int groupId;
    /**
     * role id.
     */
    private int roleId;
}
