package hoperun.pagoda.demo.bean;

import java.util.List;

import hoperun.pagoda.demo.entity.UserGroupTree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *User Groups response. 
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupsResponse {

    /**
     * user groups.
     */
    private List<UserGroupTree> groups;
}
