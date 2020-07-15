package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.demo.entity.GroupUserNum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Pie response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPieResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *group name list.
     */
    private List<String> groupNames;
    /**
     * user numbers per groups.
     */
    private List<GroupUserNum> usePie;
}
