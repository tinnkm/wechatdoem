package com.tinnkm.application.config;

import com.tinnkm.application.filter.JwtAuthenticationFilter;
import com.tinnkm.application.filter.JwtLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname SecurityConfiguration
 * @description spring security 配置
 * @date 2018/6/20 16:51
 **/
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                // 允许登陆
                .antMatchers(HttpMethod.POST, "/user/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }
}
