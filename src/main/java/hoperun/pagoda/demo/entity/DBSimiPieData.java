package hoperun.pagoda.demo.entity;

import java.util.List;

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
public class DBSimiPieData {

    /**
     * DB number.
     */
    private int value;
    /**
     * similarity category No.
     */
    private String name;

    /**
     * similarity db.
     */
    private List<String> dbName;

    /**
     * group id.
     */
    @JsonIgnore
    private int groupId;
}
