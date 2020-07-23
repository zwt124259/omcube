package com.omcube.authserver.config;

import com.omcube.authserver.authentication.dao.SmsAuthenticationProvider;
import com.omcube.authserver.filter.SmsLoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

//@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

   // @Autowired
    private UserDetailsService userDetaillsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        SmsLoginAuthenticationFilter filter = new SmsLoginAuthenticationFilter();

        filter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));
        SmsAuthenticationProvider provider = new SmsAuthenticationProvider();
        provider.setUserDetailsService(userDetaillsService);
        httpSecurity.authenticationProvider(provider).addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);

    }
}
