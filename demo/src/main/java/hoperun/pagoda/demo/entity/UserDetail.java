package hoperun.pagoda.demo.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private int user_id;
    private String username;
    private String password;
    /**
     * email.
     */
    private String eamil;
    /**
     * job title.
     */
    private String jobTitle;
    /**
     * group id.
     */
    private Long groupId;
    /**
     * photo adress.
     */
    private String photo;
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

    public UserDetail(String username, String password, String eamil, String jobTitle, Long groupId, String photo) {
        this.username = username;
        this.password = password;
        this.eamil = eamil;
        this.jobTitle = jobTitle;
        this.groupId = groupId;
        this.photo = photo;
    }
}
