package hoperun.pagoda.demo.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.utils.JwtUtils;

/**
 * token authenticate.
 * @author zhangxiqin
 *
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    /**
     * token header.
     */
    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * jwtUtils.
     */
    @Resource
    private JwtUtils jwtUtils;

    /**
     * token verify.
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws ServletException, IOException {
        String method = "doFilterInternal";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "do filter started");
        }
        // get token
        String authToken = request.getHeader(this.tokenHeader);
        final String preToken = Constant.TOKEN_TYPE;
        if (!StringUtils.isEmpty(authToken) && authToken.startsWith(preToken)) {
            authToken = authToken.substring(preToken.length());
        } else {
            authToken = null;
        }
        // get user name from token
        String username = jwtUtils.getUsernameFromToken(authToken);
        LOGGER.info(Constant.LOG_PATTERLN, method, "Checking authentication for username.");
        if (jwtUtils.containToken(username, authToken) && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetail userDetail = jwtUtils.getUserFromToken(authToken);
            boolean validated = jwtUtils.validateToken(authToken, userDetail);
            if (Boolean.TRUE.equals(validated)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                        userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info(String.format("Authenticated userDetail %s, setting security context", username));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}