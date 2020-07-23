package com.omcube.authserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.omcube.authserver.dao.UserDao;
import com.omcube.authserver.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userDetaillsService")
public class UserDetaillsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("account",name);

        UserEntity userEntity=userDao.selectOne(queryWrapper);

        return userEntity;
    }


}
