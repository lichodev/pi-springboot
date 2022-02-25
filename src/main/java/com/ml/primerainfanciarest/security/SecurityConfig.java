package com.ml.primerainfanciarest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder.passwordEncoder());
    }

    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/experiences", "/gallery", "/podcast", "/recorders", "/tips", "/workshops").permitAll()
                .antMatchers(HttpMethod.GET, "/experiences/pending", "/questions", "/requests").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/experiences", "/question", "/request", "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/gallery", "/podcast", "/tips", "/recorder").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/tips", "/recorder").permitAll()
                .antMatchers(HttpMethod.PUT, "/experiences", "/questions", "/requests").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/experiences").hasRole("ADMIN")
                .and()
                .exceptionHandling();
    }
}
