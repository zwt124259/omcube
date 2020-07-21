package com.omcube.authserver.authentication.dao;

import com.omcube.authserver.authentication.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsCaptchaAuthenticationToken= (SmsAuthenticationToken) authentication;
        UserDetails user=userDetailsService.loadUserByUsername((String) smsCaptchaAuthenticationToken.getPrincipal());
        if(user==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //认证通过
        SmsAuthenticationToken authenticationTokenResult=new SmsAuthenticationToken(smsCaptchaAuthenticationToken.getPrincipal(),user,user.getAuthorities());
        //将之前未认证的请求放进认证后的Token中
        authenticationTokenResult.setDetails(smsCaptchaAuthenticationToken.getDetails());
        return authenticationTokenResult;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(SmsAuthenticationToken.class);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
