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
     *userId. 
     */
    private int userId;
    /**
     *groupId. 
     */
    private int groupId;

    /**
     *acceptor. 
     */
    private String acceptor;
    /**
     *sender. 
     */
    private String sender;
    /**
     *type. 
     */
    private int type;
    /**
     *date. 
     */
    private String createdate;
    /**
     *description. 
     */
    private String description;

}
