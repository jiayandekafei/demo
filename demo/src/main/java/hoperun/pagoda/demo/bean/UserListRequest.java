package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.demo.entity.Group;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User list response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * user name.
     */
    @ApiModelProperty(value = "username", required = true)
    private int userId;
    /**
     ** is super user.
     */
    @ApiModelProperty(value = "is super user", required = true)
    private String superuser;
    /**
     * groups.
     */
    @ApiModelProperty(value = "groups")
    private List<Group> groups;
}
