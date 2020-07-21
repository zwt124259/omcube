package com.omcube.gateway.factory;

import com.omcube.gateway.feign.AuthServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthSignatureFallbackFactory implements FallbackFactory<AuthServiceClient> {

    @Override
    public AuthServiceClient create(Throwable throwable) {
        return new AuthServiceClient(){
            @Override
            public Map<String, Object> checkToken(Map<String, ?> param) {

                Map<String,Object> result = new HashMap<>();

                result.put("msg",throwable.getMessage());

                return result;
            }
        };
    }
}
