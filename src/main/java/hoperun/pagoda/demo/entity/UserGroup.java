package hoperun.pagoda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
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
    /**
     * status.
     */
    private String status;
}
