package hoperun.pagoda.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.mapper.UserMapper;

/**
 * user detail service.
 * @author zhangxiqin
 *
 */
@Component(value = "CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    /**
     * user mapper.
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * retrieve user info by username.
     */
    @Override
    public UserDetail loadUserByUsername(final String name) {
        UserDetail userDetail = userMapper.findByUsername(name);
        if (userDetail == null) {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        return userDetail;
    }
}