package hoperun.pagoda.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.entity.User;
import hoperun.pagoda.demo.exception.UsernameIsExitedException;
import hoperun.pagoda.demo.repository.UserRepository;
import io.swagger.annotations.ApiOperation;

/**
 * User Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * Get User List.
     *
     * @return
     */
    @ApiOperation(value = "get user list", notes = "get user list")
    @GetMapping("/list")
    public Map<String, Object> userList() {
        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "userList", "get user list started");
        }
        List<User> users = userRepository.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", users);
        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "userList", "get user list ended");
        }
        return map;
    }

    /**
     * User register.
     * 
     * @param user
     */
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        User bizUser = userRepository.findByUsername(user.getUsername()s);
        if (null != bizUser) {
            throw new UsernameIsExitedException("user already exist.");
        }
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
        return userRepository.save(user);
    }

}
