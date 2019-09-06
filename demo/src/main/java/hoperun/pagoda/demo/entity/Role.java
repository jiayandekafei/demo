package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Role.
 * 
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    private long role_id;
    private String role;
}
