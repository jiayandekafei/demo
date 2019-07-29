package hoperun.pagoda.demo.bean;

import hoperun.pagoda.demo.entity.UserDetail;
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
public class UserResponse {
    private String token;
    private UserDetail userDetail;
}
