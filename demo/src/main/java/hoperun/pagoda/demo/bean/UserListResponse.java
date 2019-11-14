package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

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
public class UserListResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * user list.
     */
    private transient List<UserDetailResponse> users;
    /**
     * user total.
     */
    private int total;
}
