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
public class ExeStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *  id.
     */
    private int id;

    /**
     * group id.
     */
    private int groupId;

    /**
     * execute type ,1:DB export, 2 :DB similarity .
     */
    private int type;

    /**
     * execute status, W:executing,C:completed.
     */
    private String status;

}
