package com.doeveryday.doeverydaysecurity.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * H2 console enabled on .../console
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@Profile("default")
public class AppSecurityH2ConsoleConfig extends WebSecurityConfigurerAdapter {

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    public AppSecurityH2ConsoleConfig(DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?failedlogin=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .and()
//                Config for h2 console
                .headers()
                .frameOptions()
                .disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(daoAuthenticationProvider);
    }

//    Config for h2 console
    @SuppressWarnings("rawtypes")
    @Profile("default")
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        //noinspection unchecked
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}