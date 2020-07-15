package hoperun.pagoda.demo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * group node.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupNode {
    /**
     *  id.
     */
    private String id;
    /**
     *  lable.
     */
    private String label;
    /**
     *  children.
     */
    private List<RoleNode> children;
    /**
     *  radio.
     */
    private String radio;
    /**
     *  check box.
     */
    private boolean checked;

}
