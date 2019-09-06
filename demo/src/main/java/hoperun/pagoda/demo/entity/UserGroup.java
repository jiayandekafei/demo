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
     * user id
     */

    private int user_id;
    /**
     * group id
     */
    private int group_id;
    /**
     * role id
     */
    private int role_id;
}
