package hoperun.pagoda.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  user delete info.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {

    /**
     * user id.
     */
    private int userId;
    /**
     * group id.
     */
    private int groupId;
    /**
     * group length.
     */
    private int groupLength;
}
