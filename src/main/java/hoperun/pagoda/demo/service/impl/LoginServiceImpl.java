package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.LoginResponse;
import hoperun.pagoda.demo.bean.MessageResponse;
import hoperun.pagoda.demo.bean.StatisticsResponse;
import hoperun.pagoda.demo.bean.UserDetailResponse;
import hoperun.pagoda.demo.bean.UserRegisterRequest;
import hoperun.pagoda.demo.entity.Message;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.mapper.CustomerMapper;
import hoperun.pagoda.demo.mapper.GroupMapper;
import hoperun.pagoda.demo.mapper.MessageMapper;
import hoperun.pagoda.demo.mapper.UserMapper;
import hoperun.pagoda.demo.service.LoginService;
import hoperun.pagoda.demo.utils.JwtUtils;

/**
 * 
 * @author zhangxiqin
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * authentication manager.
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * token util.
     */
    @Autowired
    private JwtUtils jwtTokenUtil;
    /**
     * token header.
     */
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /**
     * user mapper.
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * user mapper.
     */
    @Autowired
    private GroupMapper groupMapper;
    /**
     * user mapper.
     */
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * message mapper.
     */
    @Autowired
    private MessageMapper messageMapper;

    /**
     * register.
     */
    @Override
    @Transactional
    public UserDetail register(final UserRegisterRequest request) {
        UserDetail bizUser = userMapper.findByUsername(request.getUsername());
        if (null != bizUser) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, "User aready exist!"));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetail userDetail = new UserDetail(request.getUsername(), encoder.encode(request.getPassword()), request.getEmail(),
                request.getJobTitle(), "N", "W");
        // insert user bace info
        userMapper.insert(userDetail);

        return userDetail;
    }

    /**
     *login .
     */
    @Override
    public LoginResponse login(final String username, final String password) {
        // User Authentication
        final Authentication authentication = authenticate(username, password);
        // set authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // gengerate token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateAccessToken(userDetail);
        // set token
        jwtTokenUtil.putToken(username, token);
        return new LoginResponse(token, userDetail.getUserId(), userDetail.getUsername(), userDetail.getSuperuser());
    }

    /**
     * log out.
     */
    @Override
    public void logout(final String token) {
        String tempToken = token.substring(tokenHead.length() - 1);
        String userName = jwtTokenUtil.getUsernameFromToken(tempToken);
        jwtTokenUtil.deleteToken(userName);

    }

    /**
     * refresh.
     */
    @Override
    public UserDetailResponse refresh(final String oldToken) {
        return null;
    }

    /**
     * user authenticate.
     * @param username user name
     * @param password password
     * @return Authentication Authentication
     */
    private Authentication authenticate(final String username, final String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new BusinessException(BaseResponse.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

    /**
     * retrieve message.
     */
    @Override
    public List<MessageResponse> retrieveMessage(final String username) {
        List<Message> messages = Optional.ofNullable(messageMapper.findByUsername(username.concat(","))).orElse(new ArrayList<>());
        List<MessageResponse> list = new ArrayList<>();
        Map<String, List<Message>> msgMap = messages.stream().collect(Collectors.groupingBy(Message::getCreatedate));
        msgMap.forEach((k, v) -> {
            List<String> message = new ArrayList<>();
            for (Message m : v) {
                String msg = m.getDescription();
                message.add(msg);
            }
            list.add(MessageResponse.builder().messages(message).createDate(k).build());
        });
        list.sort(Comparator.comparing(MessageResponse::getCreateDate).reversed());
        return list;
    }

    /**
     *retrieve statistics info. 
     */
    @Override
    public StatisticsResponse retrieveStatisticsInfo() {
        // count of user
        int userCount = userMapper.findUserCount("");
        // count of group
        int groupCount = groupMapper.findGroupCount();
        // count of customer
        int customerCount = customerMapper.findCustomerCount();
        // count of waittting approve user
        int waitUserCount = userMapper.findUserCount("W");

        return StatisticsResponse.builder().sumUser(userCount).sumGroup(groupCount).sumCustomer(customerCount).sumWaitUser(waitUserCount).build();
    }

}
