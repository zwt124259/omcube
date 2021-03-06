package com.omcube.authserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.omcube.authserver.entity.ClientEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientDao extends BaseMapper<ClientEntity> {

    public ClientEntity loadClientByClientId(String clientId);


}
