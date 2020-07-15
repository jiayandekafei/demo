package hoperun.pagoda.demo.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * agent Info request.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AAETransformRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * DB names.
     */
    private String dbName;
    /**
     * type.
     */
    private int type;
    /**
     * group.
     */
    private int groupId;
    /**
     * color.
     */
    private String color;
}
