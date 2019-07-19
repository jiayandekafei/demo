package hoperun.pagoda.demo.service.impl;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.entity.User;
import hoperun.pagoda.demo.repository.UserRepository;

/**
 * 
 * @author zhangxiqin
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * 通过构造器注入UserRepository
     * 
     * @param userRepository
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
    }

}
