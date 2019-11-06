package com.alex.springBoot.springBootApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email AS principal, password AS credentials, true FROM user WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT user_email AS principal, role_name AS role FROM user_roles WHERE user_email = ?")
                .passwordEncoder(passwordEncoder())
                .rolePrefix("ROLE_");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/register", "/about", "/css/**", "/webjars/**")
                .permitAll()
                .antMatchers("/profile").hasAnyRole("USER,ADMIN")
                .antMatchers("/users","addTask").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/profile")
                .and().logout().logoutSuccessUrl("/login");
    }
}
