package sk.ygor.stackoverflow.q53220918.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import sk.ygor.stackoverflow.q53220918.controller.AccountController;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    public WebSecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable(); // TODO: keep only in dev
        http.addFilterAfter(jwtAuthorizationFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers(AccountController.PATH_POST_SIGN_UP).permitAll()
                .antMatchers(AccountController.PATH_POST_REFRESH).permitAll()
                .antMatchers(AccountController.PATH_POST_LOGIN).permitAll()
                .anyRequest().authenticated()
        ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setHeader("WWW-Authenticate", "Bearer"); // we can point explicitly to register/login/refresh URL
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
