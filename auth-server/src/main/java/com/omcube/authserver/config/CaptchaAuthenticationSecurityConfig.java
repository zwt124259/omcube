package com.omcube.authserver.config;

import com.omcube.authserver.authentication.dao.CaptchaAuthenticationProvider;
import com.omcube.authserver.filter.LoginCaptchaAuthenticationFilter;
import com.omcube.authserver.filter.SmsLoginAuthenticationFilter;
import com.omcube.authserver.handler.LoginFailHandler;
import com.omcube.authserver.handler.LoginSuccessHandler;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CaptchaAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetaillsService;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailHandler loginFailHandler;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        LoginCaptchaAuthenticationFilter filter = new LoginCaptchaAuthenticationFilter();
        //自定义认证通过处理
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        filter.setAuthenticationFailureHandler(loginFailHandler);

        filter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));
        CaptchaAuthenticationProvider provider = new CaptchaAuthenticationProvider();
        provider.setUserDetailsService(userDetaillsService);
        provider.setRedissonClient(redissonClient);
        httpSecurity.authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);



    }
}
