package com.omcube.authserver.service.impl;

import com.omcube.authserver.dao.ClientDao;
import com.omcube.authserver.entity.ClientEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


@Service("clientDetailsCustomService")
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private ClientDao clientDao;

    /**
     * 实现客户端数据库设置
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        ClientEntity clientDetails = new ClientEntity();

        clientDetails=clientDao.loadClientByClientId(clientId);

        return clientDetails;
    }
}
