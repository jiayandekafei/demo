package hoperun.pagoda.demo.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Change password request.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequest {

    /**
     * user id.
     */
    @ApiModelProperty(value = "userId", required = true)
    @NotNull
    @NotBlank
    private int userId;

    /**
     * status.
     */
    @ApiModelProperty(value = "password", required = true)
    @NotNull
    @NotBlank
    private String password;
}
