package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.dxlAnalyse.quantity.entity.FormInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * form Info response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormInfoResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * form list.
     */
    private List<FormInfo> formInfo;

    /**
     * form count.
     */
    private int total;

}
