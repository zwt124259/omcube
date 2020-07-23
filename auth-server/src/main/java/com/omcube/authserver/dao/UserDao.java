package com.omcube.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.omcube.authserver.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
