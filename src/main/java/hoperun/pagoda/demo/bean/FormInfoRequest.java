package hoperun.pagoda.demo.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * form Info request.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormInfoRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * DB names.
     */
    private String dbName;
    /**
     * pageNo.
     */
    private int pageNo;
    /**
     * limit.
     */
    private int pageSize;
    /**
     * form name.
     */
    private String formName;

}
