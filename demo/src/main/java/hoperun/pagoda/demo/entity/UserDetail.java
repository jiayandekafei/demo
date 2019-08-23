package hoperun.pagoda.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private long user_id;
    private String username;
    private String password;
    private Role role;

    public UserDetail(long id, String username, String password, Role role) {
        this.user_id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDetail(long id, String username, String password) {
        this.user_id = id;
        this.username = username;
        this.password = password;
    }

    public UserDetail(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
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
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return authorities;
    }
}
