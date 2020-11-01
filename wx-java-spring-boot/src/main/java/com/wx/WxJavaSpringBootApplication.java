package com.wx;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
//@Cacheable
//@Async
@EnableConfigurationProperties
public class WxJavaSpringBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(WxJavaSpringBootApplication.class, args);
    }


}
