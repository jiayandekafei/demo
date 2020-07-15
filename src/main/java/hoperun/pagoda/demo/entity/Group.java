package hoperun.pagoda.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * group.
 * 
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * group id.
     */
    private int groupId;
    /**
     * group name.
     */
    private String groupname;
    /**
     * group customer id.
     */
    private int customerId;
    /**
     * notes server.
     */
    private String server;
    /**
     * notes server user.
     */
    private String serverUser;
    /**
     * notes server password.
     */
    private String serverPassword;
    /**
     * group descriptions.
     */
    private String description;
    /**
     * customerName.
     */
    private String customername;
    /**
     * notes DB path.
     */
    private String notesDBPath;
    /**
     * notes DB export result path.
     */
    private String exportResultPath;
}
