package hoperun.pagoda.demo.bean;


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
@ToString
public class UserResponse {
    private String username;
    private String password;

}
