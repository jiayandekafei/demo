package hoperun.pagoda.demo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupNode {
    private String id;
    private String label;
    private List<RoleNode> children;
    private String radio;
    private boolean checked;

}
