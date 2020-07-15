package hoperun.pagoda.demo.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * batch approve request.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchApproveRequest {

    private List<UserRequest> users;
}
