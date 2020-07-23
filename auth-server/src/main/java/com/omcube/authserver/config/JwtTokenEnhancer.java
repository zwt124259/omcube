package com.omcube.authserver.config;

import com.omcube.authserver.entity.UserEntity;
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

        if(principal!=null){
            UserEntity userEntity = (UserEntity)principal;
            userEntity.setPassword(null);
            objectMap.put("username",userEntity);
        }
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(objectMap);

        return accessToken;
    }
}
