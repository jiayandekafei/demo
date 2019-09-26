package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * group.
 * 
 * @author zhangxiqin
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private int group_id;
    private String groupname;
    private int customer_id;
    private String notes_server;
    private String notes_server_user;
    private String notes_server_pasword;
    private String description;
    // private String customername;
}
