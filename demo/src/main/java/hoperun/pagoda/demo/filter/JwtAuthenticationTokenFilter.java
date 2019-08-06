package hoperun.pagoda.demo.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import hoperun.pagoda.demo.entity.UserDetail;
import hoperun.pagoda.demo.utils.JwtUtils;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String token_header;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String auth_token = request.getHeader(this.token_header);
        final String auth_token_start = "Bearer ";
        if (!StringUtils.isEmpty(auth_token) && auth_token.startsWith(auth_token_start)) {
            auth_token = auth_token.substring(auth_token_start.length());
        } else {
            auth_token = null;
        }

        String username = jwtUtils.getUsernameFromToken(auth_token);
        logger.info(String.format("Checking authentication for userDetail %s.", username));

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