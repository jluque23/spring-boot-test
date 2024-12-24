package com.globalsoftwaresupport.spring.config;

import com.globalsoftwaresupport.spring.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Use your CustomUserDetailsService for authentication
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String ADMIN = "ADMIN";
        String USER = "USER";

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/getall").hasAnyRole(ADMIN, USER)
                .antMatchers("/api/users/add").hasRole(ADMIN)
                .antMatchers("/api/products/getall").hasAnyRole(ADMIN, USER)
                .antMatchers("/api/products/add").hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // or use formLogin() for web forms
    }
}
