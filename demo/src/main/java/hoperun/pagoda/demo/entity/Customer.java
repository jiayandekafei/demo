package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * Customer.
 * 
 * @author zhangxiqin
 *
 */
@Data
@Builder
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int customer_id;
    private String customername;
    private String description;
}
