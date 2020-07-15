package hoperun.pagoda.demo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user group tree.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupTree {
    /**
     * lable.
     */
    private String label;
    /**
     * children.
     */
    private List<GroupNode> children;

}
