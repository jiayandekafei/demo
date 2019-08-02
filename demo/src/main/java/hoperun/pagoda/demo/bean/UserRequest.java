package hoperun.pagoda.demo.bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
public class UserRequest {
    @ApiModelProperty(value = "username", required = true)
    @Size(min = 6, max = 30)
    private String username;
    @ApiModelProperty(value = "password", required = true)
    @Pattern(regexp = "/^[\\\\w_-]{6,16}$/")
    private String password;
}
