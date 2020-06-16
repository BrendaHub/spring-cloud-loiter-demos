package com.redis.distributed.lock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Fun Description //TODO
 * @Date 2020/6/16 14:58 16
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Component
@ConfigurationProperties(prefix = "redis.config")
@Data
public class RedisConfig {

    private int max_idle;
    private int max_wait;
    private String host;
    private int port;
    private String password;
    private int retry_num;
    private int timeout;

}
