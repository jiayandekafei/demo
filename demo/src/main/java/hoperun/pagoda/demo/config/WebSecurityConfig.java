package hoperun.pagoda.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

import hoperun.pagoda.demo.filter.JwtAuthenticationTokenFilter;

/**
 * Web Security Config.
 * @author zhangxiqin
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * user detail service.
     */
    private final UserDetailsService customUserDetailsService;
    /**
     * token authentication filter.
     */
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * Constructor with parameter.
     * @param mCustomUserDetailsService customUserDetailsService
     * @param mAuthenticationTokenFilter authenticationTokenFilter
     */
    @Autowired
    public WebSecurityConfig(@Qualifier("CustomUserDetailsService") final UserDetailsService mCustomUserDetailsService,
            final JwtAuthenticationTokenFilter mAuthenticationTokenFilter) {
        this.customUserDetailsService = mCustomUserDetailsService;
        this.authenticationTokenFilter = mAuthenticationTokenFilter;
    }

    /**
     * Config authentication.
     * @param authenticationManagerBuilder authenticationManagerBuilder
     * @throws Exception Exception
     */
    @Autowired
    public void configureAuthentication(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Password encoder.
     * @return encoded password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * http security config.
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/login", "/register", "/user/check/*", "/error/**").permitAll().anyRequest().authenticated();
        httpSecurity.headers().cacheControl();
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * web security config.
     */
    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers("swagger-ui.html", "**/swagger-ui.html", "/favicon.ico", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.gif",
                "/swagger-resources/**", "/v2/**", "/**/*.ttf");
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
                "/swagger-resources/configuration/security", "/swagger-ui.html");
    }

    /**
     * cors security config.
     * @return  CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * authenticationManagerBean.
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
