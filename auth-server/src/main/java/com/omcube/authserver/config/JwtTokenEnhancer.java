package com.omcube.authserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Object principal=authentication.getPrincipal();

        Map<String,Object> objectMap = new HashMap<>();

        objectMap.put("username",principal);

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(objectMap);

        return accessToken;
    }
}
