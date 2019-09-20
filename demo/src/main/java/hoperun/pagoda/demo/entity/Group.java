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
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private int group_id;
    private String groupname;
    private int customer_id;
    private String notes_server;
    private String notes_server_user;
    private String notes_server_pasword;
    private String description;
    private String customername;
}
