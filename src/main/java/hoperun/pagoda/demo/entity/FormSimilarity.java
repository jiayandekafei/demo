package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form similarity.
 * 
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormSimilarity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id.
     */
    private int id;
    /**
     * db name.
     */
    private String dbName;
    /**
     * all category number per DB.
     */
    private int allCategory;
    /**
     * form name.
     */
    private String formName;
    /**
     * category size per form.
     */
    private int categorySize;
    /**
     * category size.
     */
    private int categoryNo;
    /**
     * field number.
     */
    private int fieldNum;
    /**
     * code number.
     */
    private int codeNum;

}
