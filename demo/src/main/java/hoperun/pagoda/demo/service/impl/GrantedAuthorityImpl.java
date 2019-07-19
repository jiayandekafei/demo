package hoperun.pagoda.demo.service.impl;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author zhangxiqin
 *
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
