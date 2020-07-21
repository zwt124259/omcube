package com.omcube.gateway.filter;

import com.omcube.gateway.feign.AuthServiceClient;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    private static final String BEARER = "Bearer";

    @Resource
    private AuthServiceClient authServiceClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取toekn
        String authorization=exchange.getRequest().getHeaders().getFirst("Authorization");

        //校验token是否合法
        if(!StringUtils.startsWithIgnoreCase(authorization,BEARER)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String[] authorizations=authorization.split(" ");

        String access_token = authorizations[1];

        Map<String,Object> param = new HashMap<>();

        param.put("token",access_token);
        try {
            Map<String,Object> respObj=authServiceClient.checkToken(param);
            //认证合法
        }catch (Exception e){
           exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

           throw e;
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
