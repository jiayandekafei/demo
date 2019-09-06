package hoperun.pagoda.demo.bean;

import java.util.List;

import hoperun.pagoda.demo.entity.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User response.
 *
 * @author zhangxiqin
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetailResponse {
    /**
     * userId.
     */
    private long userId;
    /**
     * userId.
     */
    private String username;
    /**
     * status.
     */
    private String status;
    /**
     * email.
     */
    private String email;
    /**
     * job title.
     */
    private String jobTitle;
    /**
     * is super user.
     */
    private String superuser;
    /**
     * photo address.
     */
    private String photo;
    /**
     * groups.
     */
    private List<UserGroup> groups;

}
