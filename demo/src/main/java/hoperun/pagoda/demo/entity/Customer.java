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
    private int customer_id;
    private String customername;
    private String description;

    public Customer(String customername, String description) {
        this.customername = customername;
        this.description = description;
    }

}
