package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.dxlAnalyse.quantity.entity.DBInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB Info response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBInfoResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * DB list.
     */
    private List<DBInfo> dbInfo;

    /**
     * DB count.
     */
    private int total;

}
