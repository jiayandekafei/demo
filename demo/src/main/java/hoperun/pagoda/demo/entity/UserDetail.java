package hoperun.pagoda.demo.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user details.
 * @author zhangxiqin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    /**
     * user id.
     */
    private int userId;
    /**
     * user name.
     */
    private String username;
    /**
     * password.
     */
    private String password;
    /**
     * email.
     */
    private String email;
    /**
     * job title.
     */
    private String jobTitle;

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

    /**
     * Constructor.
     * @param mId id
     * @param mUsername username
     * @param mPassword passwords
     */
    public UserDetail(final int mId, final String mUsername, final String mPassword) {
        this.userId = mId;
        this.username = mUsername;
        this.password = mPassword;
    }

    /**
     * Constructor.
     * @param mUsername user name
     * @param mPassword password
     */
    public UserDetail(final String mUsername, final String mPassword) {
        this.username = mUsername;
        this.password = mPassword;
    }

    /**
     * is account non expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * is account non locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * is credential non expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * is enable.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * get authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    /**
     * Constructor.
     * @param mUsername user name
     * @param mPassword password
     * @param mEmail email
     * @param mJobTitle job tile
     * @param mSuperuser is super user
     * @param mStatus status
     */
    public UserDetail(final String mUsername, final String mPassword, final String mEmail, final String mJobTitle, final String mSuperuser,
            final String mStatus) {
        this.username = mUsername;
        this.password = mPassword;
        this.email = mEmail;
        this.jobTitle = mJobTitle;
        this.superuser = mSuperuser;
        this.status = mStatus;

    }
}
