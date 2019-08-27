package hoperun.pagoda.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User response.
 *
 * @author zhangxiqin
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse {
    /**
     * token.
     */
    private String token;
    /**
     * userId.
     */
    private int userId;
    /**
     * username.
     */
    private String username;

}
