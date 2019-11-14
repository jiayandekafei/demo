package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import hoperun.pagoda.demo.entity.Customer;
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
public class CustomerListResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * customer list.
     */
    private List<Customer> customers;
    /**
     * total customer.
     */
    private int total;
}
