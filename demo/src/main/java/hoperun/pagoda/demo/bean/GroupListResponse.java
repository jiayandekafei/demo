package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.demo.entity.Group;
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
public class GroupListResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * group list.
     */
    private List<Group> groups;
    /**
     * total group.
     */
    private int total;
}
