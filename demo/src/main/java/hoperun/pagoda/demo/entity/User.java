package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * Role.
 * 
 * @author zhangxiqin
 *
 */
@Data
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String username;
    private String password;
}
