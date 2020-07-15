package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB similarity.
 * 
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DBSimilarity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id.
     */
    private int id;
    /**
     * group id.
     */
    private int groupId;
    /**
     * category No.
     */
    private String categoryNo;
    /**
     * DB name (English).
     */
    private String enName;
    /**
     * DB name (Japanese).
     */
    private String jpName;
    /**
     * main form.
     */
    private String mainForm;
    /**
     * categorySize.
     */
    private int categorySize;
    /**
     * dbSize.
     */
    private int dbSize;
}
