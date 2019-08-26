package hoperun.pagoda.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.mapper.UserMapper;

@Component(value = "CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetail userDetail = userMapper.findByUsername(name);
        if (userDetail == null) {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        /*
         * Role role = userMapper.findRoleByUserId(userDetail.getUser_id()); userDetail.setRole(role);
         */
        return userDetail;
    }
}