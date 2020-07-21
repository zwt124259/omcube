package com.omcube.authserver.service.impl;

import com.omcube.authserver.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetaillsService")
public class UserDetaillsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(s);

        userEntity.setPassword(new BCryptPasswordEncoder().encode("123456"));

        return userEntity;
    }
}
