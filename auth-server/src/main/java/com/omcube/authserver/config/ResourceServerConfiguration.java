package com.omcube.authserver.config;

import com.omcube.authserver.handler.LoginFailHandler;
import com.omcube.authserver.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Resource
    LoginSuccessHandler loginSuccessHandler;
    @Resource
    LoginFailHandler loginFailHandler;

    @Resource
    AuthenticationEntryPoint commAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(commAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .cors().disable();
    }
}
