package com.omcube.authserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;

@Data
@TableName("oauth_client_details")
public class ClientEntity implements ClientDetails {

    @TableId
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String registeredRedirectUri;

    private String authorities;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private String additionalInformation;



    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        Set<String> resourceIdsSet = new HashSet<String>();

        if(resourceIds!=null){
            String[] scopeArray=StringUtils.split(resourceIds,",");

            resourceIdsSet.addAll( Arrays.asList(resourceIds));
        }
        return resourceIdsSet;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {

        Set<String> scopeSet = new HashSet<String>();

        if(scope!=null){
            String[] scopeArray=StringUtils.split(scope,",");

            scopeSet.addAll( Arrays.asList(scope));
        }
        return scopeSet;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {

        Set<String> authorizedGrantTypesSet = new HashSet<String>();

        if(authorizedGrantTypes!=null){
            String[] scopeArray=StringUtils.split(authorizedGrantTypes,",");

            authorizedGrantTypesSet.addAll( Arrays.asList(authorizedGrantTypes));
        }
        return authorizedGrantTypesSet;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {

        Set<String> registeredRedirectUriSet = new HashSet<String>();

        if(registeredRedirectUri!=null){
            String[] scopeArray=StringUtils.split(registeredRedirectUri,",");

            registeredRedirectUriSet.addAll( Arrays.asList(registeredRedirectUri));
        }
        return registeredRedirectUriSet;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();

        if(authorities!=null){
            String[] scopeArray=StringUtils.split(authorities,",");

            for (String str:scopeArray) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(str);
                authoritiesSet.add(grantedAuthority);
            }
        }
        return authoritiesSet;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
