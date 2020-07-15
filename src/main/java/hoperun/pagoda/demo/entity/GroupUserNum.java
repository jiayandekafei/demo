package hoperun.pagoda.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user group.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupUserNum {

    /**
     * user number.
     */
    private int value;
    /**
     * group name.
     */
    private String name;

    /**
     * group id.
     */
    @JsonIgnore
    private int groupId;
}
