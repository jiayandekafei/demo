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
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String message;

}
