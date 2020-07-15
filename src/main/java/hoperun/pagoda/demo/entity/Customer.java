package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer.
 * 
 * @author zhangxiqin
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * customer id.
     */
    private int customerId;
    /**
     * customer name.
     */
    private String customername;
    /**
     * customer description.
     */
    private String description;

    /**
     * Constructor.
     * @param mCustomername customername
     * @param mDescription description
     */
    public Customer(final String mCustomername, final String mDescription) {
        this.customername = mCustomername;
        this.description = mDescription;
    }

}
