package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.demo.entity.FormSimilarity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User list response.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSimiListResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * similarity list.
     */
    private List<FormSimilarity> simiData;

}
