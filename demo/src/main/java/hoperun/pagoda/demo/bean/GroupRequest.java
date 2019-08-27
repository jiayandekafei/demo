package hoperun.pagoda.demo.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user register request.
 * 
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequest {
    /**
     * group name.
     */
    @ApiModelProperty(value = "groupname", required = true)
    private String groupname;
    /**
     * customerId.
     */
    @ApiModelProperty(value = "customerID")
    private String customerId;
    /**
     * notes_server.
     */
    @ApiModelProperty(value = "notes server address")
    private String notes_server;
    /**
     * notes server user.
     */
    @ApiModelProperty(value = "notes server user")
    private String notes_server_user;
    /**
     * notes server password.
     */
    @ApiModelProperty(value = "notes server password")
    private String notes_server_password;
    /**
     * description.
     */
    @ApiModelProperty(value = "description")
    private String description;

}
