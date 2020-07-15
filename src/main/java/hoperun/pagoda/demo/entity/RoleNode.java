package hoperun.pagoda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * role node for group tree.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleNode {
    /**
     * id.
     */
    private String id;
    /**
     * lable.
     */
    private String label;
}
