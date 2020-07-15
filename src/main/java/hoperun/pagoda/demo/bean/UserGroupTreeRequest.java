package hoperun.pagoda.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *User group tree request. 
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupTreeRequest {

    /**
     * userId.
     */
    private int userId;
    /**
    * groups.
    */
    private String groups;

    /**
     * is super user.
     */
    private String superuser;

    /**
     * 1:when add user. 2: when eidt user. 3:when modify self info.
     * 
     */
    private int type;
    /**
     * current group id. only needed when edit user.
     */

    private int groupId;

    /**
     * current role id. only needed when edit user.
     */
    private int roleId;
}
