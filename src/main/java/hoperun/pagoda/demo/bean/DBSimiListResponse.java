package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.demo.entity.DBSimilarity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB similarity response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBSimiListResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * similarity list.
     */
    private List<DBSimilarity> simiData;

}
