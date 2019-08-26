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
public class UserRegisterRequest {
    /**
     * user name.
     */
    @ApiModelProperty(value = "username", required = true)
    // @Size(min = 6, max = 30)
    private String username;
    /**
     * password.
     */
    @ApiModelProperty(value = "password", required = true)
    // @Pattern(regexp = "/^[\\\\w_-]{6,16}$/")
    private String password;
    /**
     * email.
     */
    @ApiModelProperty(value = "eamil", required = true)
    // @Pattern(regexp = "/^[\\\\w_-]{6,16}$/")
    private String eamil;
    /**
     * job title.
     */
    @ApiModelProperty(value = "job title")
    private String jobTitle;
    /**
     * group id.
     */
    @ApiModelProperty(value = "group id")
    private Long groupId;
}
