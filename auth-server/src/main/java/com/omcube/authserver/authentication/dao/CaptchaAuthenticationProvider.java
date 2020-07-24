package com.omcube.authserver.authentication.dao;

import com.omcube.authserver.authentication.CaptchaAuthenticationToken;
import com.omcube.authserver.constant.Constant;
import com.omcube.authserver.entity.CaptchaAuthEntity;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

public class CaptchaAuthenticationProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;

    private RedissonClient redissonClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CaptchaAuthenticationToken captchaAuthenticationToken= (CaptchaAuthenticationToken) authentication;

        CaptchaAuthEntity captchaAuthEntity = (CaptchaAuthEntity) captchaAuthenticationToken.getCredentials();

        UserDetails user=userDetailsService.loadUserByUsername(captchaAuthEntity.getUsername());

        if(user==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        String reqId=(String)captchaAuthenticationToken.getPrincipal();
        //缓存中获取图形验证码
        RMapCache rMapCache =redissonClient.getMapCache(Constant.AUTH_CAPTCHA_CACHE_KEY);

        String cacheValue=(String)(rMapCache.get(reqId)!=null?rMapCache.get(reqId):"");

        //验证码校验
        if(!cacheValue.equals(captchaAuthEntity.getCaptcha())){
            rMapCache.remove(reqId);
            throw new InternalAuthenticationServiceException("验证码错误");
        }
        //用户名密码校验
        if(!new BCryptPasswordEncoder().matches(captchaAuthEntity.getPassword(),user.getPassword())){
            throw new InternalAuthenticationServiceException("密码错误");
        }

        //认证通过
        CaptchaAuthenticationToken authenticationTokenResult=new CaptchaAuthenticationToken(user,captchaAuthenticationToken.getPrincipal(),user.getAuthorities());

        //将之前未认证的请求放进认证后的Token中
        authenticationTokenResult.setDetails(captchaAuthenticationToken.getDetails());

        return authenticationTokenResult;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(CaptchaAuthenticationToken.class);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
}
