package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import hoperun.pagoda.demo.entity.DBSimiPieData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB similarity Pie response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DBSimiPieResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *DB category No.
     */
    private Set<String> categoryNo;
    /**
     * user numbers per groups.
     */
    private List<DBSimiPieData> dbSimiPie;
}
