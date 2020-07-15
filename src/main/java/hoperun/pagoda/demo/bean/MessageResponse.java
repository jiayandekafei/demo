package hoperun.pagoda.demo.bean;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * message response .
 * @author zhangxiqin
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    /**
     * create date.
     */
    private String createDate;
    /**
     * messages.
     */
    private List<String> messages;

}
