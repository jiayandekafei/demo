package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.dxlAnalyse.quantity.entity.ViewInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View Info response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewInfoResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * view list.
     */
    private List<ViewInfo> viewInfo;

    /**
     * view count.
     */
    private int total;

}
