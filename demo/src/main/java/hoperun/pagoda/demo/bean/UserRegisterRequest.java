package hoperun.pagoda.demo.bean;

import java.util.List;

import hoperun.pagoda.demo.entity.UserGroup;
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
    @ApiModelProperty(value = "email", required = true)
    // @Pattern(regexp = "/^[\\\\w_-]{6,16}$/")
    private String email;
    /**
     * job title.
     */
    @ApiModelProperty(value = "job title")
    private String jobTitle;
    /**
     * user group.
     */
    private List<UserGroup> groups;
}
