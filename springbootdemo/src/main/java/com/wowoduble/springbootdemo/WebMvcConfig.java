package com.wowoduble.springbootdemo;

import com.wowoduble.springbootdemo.config.RedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig   {
    @Bean
    public RedisConfig redisConfig(){
        return new RedisConfig();
    }
}
