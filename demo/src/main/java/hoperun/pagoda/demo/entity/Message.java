package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Role.
 * 
 * @author zhangxiqin
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *id. 
     */
    private int id;

    /**
     *user id. 
     */
    private int userId;
    /**
     *description. 
     */
    private String description;

}
