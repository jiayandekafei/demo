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
public class FileDownLoadRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * groupId.
     */
    private int groupId;
    /**
     * file name.
     */
    private String fileName;
    /**
     * 1:DB overview.
     * 2:DB similarity
     * 3:DB reference
     * 4:DB info
     * 5:AAE result
     */
    private int type;
}
