package hoperun.pagoda.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.entity.User;
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
        User user = new User();
        user.setUsername("admin");
        user.setPassword("*********");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        if (logger.isDebugEnabled()) {
            logger.debug("method:{},message:{}", "userList", "get user list ended");
        }
        return map;
    }

}
