package hoperun.pagoda.demo.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sinup request.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {
    /**
     * user ids
     */
    private List<Integer> userIds;
}
