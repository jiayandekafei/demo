package hoperun.pagoda.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * User entity.
 * 
 * @author zhangxiqin
 *
 */
@Entity
@Table(name = "tb_user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;

}
