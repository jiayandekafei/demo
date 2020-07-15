package hoperun.pagoda.demo.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB Info request.
 *
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBInfoRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * geoupId.
     */
    private int groupId;
    /**
     * pageNo.
     */
    private int pageNo;
    /**
     * limit.
     */
    private int pageSize;
    /**
     * English name.
     */
    private String enName;
    /**
     * Japan name.
     */
    private String jpName;

}
