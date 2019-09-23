package hoperun.pagoda.demo.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private int user_id;
    private String username;
    private String password;
    /**
     * email.
     */
    private String email;
    /**
     * job title.
     */
    private String job_title;

    /**
     * photo adress.
     */
    private String photo;
    /**
     * is super user.
     */
    private String superuser;
    /**
     * status.
     */
    private String status;
    public UserDetail(int id, String username, String password) {
        this.user_id = id;
        this.username = username;
        this.password = password;
    }

    public UserDetail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public UserDetail(String username, String password, String eamil, String jobTitle, String superuser, String status) {
        this.username = username;
        this.password = password;
        this.email = eamil;
        this.job_title = jobTitle;
        this.superuser = superuser;
        this.status = status;

    }
}
