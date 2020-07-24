package com.omcube.authserver.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redisson")
public class RedisConfiguration {

    private String address;

    @Bean
    public RedissonClient redissonClient(){

        Config config = new Config();

        config.useSingleServer().setAddress(address);

        RedissonClient redissonClient  =Redisson.create(config);

        return redissonClient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
