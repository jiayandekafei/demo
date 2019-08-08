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

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Value("${jwt.header}")
    private String token_header;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String method = "doFilterInternal";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "do filter started");
        }

        String auth_token = request.getHeader(this.token_header);
        final String auth_token_start = Constant.TOKEN_TYPE;
        if (!StringUtils.isEmpty(auth_token) && auth_token.startsWith(auth_token_start)) {
            auth_token = auth_token.substring(auth_token_start.length());
        } else {
            auth_token = null;
        }

        // get user name from token
        String username = jwtUtils.getUsernameFromToken(auth_token);
        LOGGER.info(Constant.LOG_PATTERLN, method, String.format("Checking authentication for userDetail %s.", username));
        if (jwtUtils.containToken(username, auth_token) && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetail userDetail = jwtUtils.getUserFromToken(auth_token);
            if (jwtUtils.validateToken(auth_token, userDetail)) {
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