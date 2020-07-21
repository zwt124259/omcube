package com.omcube.gateway.feign;

import com.omcube.gateway.factory.AuthSignatureFallbackFactory;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "auth-server",
        configuration = AuthServiceClient.FormSupportConfig.class,fallbackFactory = AuthSignatureFallbackFactory.class)
public interface AuthServiceClient {

    @PostMapping(value = "/oauth/check_token",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String,Object> checkToken(Map<String,?> param);


    class FormSupportConfig{
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;
        // new一个form编码器，实现支持form表单提交
        @Bean
        public Encoder feignFormEncoder() {
            return new FormEncoder();
        }
        // 开启Feign的日志
        @Bean
        public Logger.Level logger() {
            return Logger.Level.FULL;
        }

    }


}
