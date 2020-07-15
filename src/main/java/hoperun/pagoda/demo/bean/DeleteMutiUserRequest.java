package hoperun.pagoda.demo.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * delete user request.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMutiUserRequest {
    /**
     * user ids.
     */
    private List<DeleteUserRequest> users;
}
