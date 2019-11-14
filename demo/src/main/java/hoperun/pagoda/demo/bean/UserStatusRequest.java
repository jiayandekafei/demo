package hoperun.pagoda.demo.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Change user status request.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusRequest {
    /**
     * user id.
     */
    @ApiModelProperty(value = "userId", required = true)
    private int userId;
    /**
     * status.
     */
    @ApiModelProperty(value = "status", required = true)
    private String status;
}
