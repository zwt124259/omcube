package com.omcube.authserver.service.impl;

import com.omcube.authserver.entity.ClientEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service("clientDetailsCustomService")
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        ClientEntity clientDetails = new ClientEntity();

        clientDetails.setClientId("client");

        clientDetails.setClientSecret(new BCryptPasswordEncoder().encode("123456"));

        Set<String> scope = new HashSet<String>();

        scope.add("all");

        clientDetails.setScope(scope);

        Set<String> grantType = new HashSet<String>();

        grantType.add("custom");
        grantType.add("refresh_token");
        grantType.add("client_credentials");
        grantType.add("authorization_code");

        clientDetails.setAuthorizedGrantTypes(grantType);

        Set<GrantedAuthority> authorities = new HashSet<>();

        clientDetails.setAuthorities(authorities);

        Set<String> uriSet = new HashSet<>();

        uriSet.add("http://127.0.0.1:8087/login");

        clientDetails.setRegisteredRedirectUri(uriSet);

        return clientDetails;
    }
}
