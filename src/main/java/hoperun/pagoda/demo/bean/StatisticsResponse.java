package hoperun.pagoda.demo.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * retrieve statistics info.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    /**
     * count of user.
     */
    private int sumUser;

    /**
     * count of group.
     */
    private int sumGroup;

    /**
     * count of customer.
     */
    private int sumCustomer;

    /**
     * count of waitting for approve user.
     */
    private int sumWaitUser;

}
