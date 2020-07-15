package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.dxlAnalyse.quantity.entity.AgentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Agent Info response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentInfoResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * view list.
     */
    private List<AgentInfo> agentInfo;

    /**
     * view count.
     */
    private int total;

}
