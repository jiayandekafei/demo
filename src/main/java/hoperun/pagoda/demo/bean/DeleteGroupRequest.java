package hoperun.pagoda.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sinup request.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteGroupRequest {
    /**
     * groups ids.
     */
    private String groupIds;
}
