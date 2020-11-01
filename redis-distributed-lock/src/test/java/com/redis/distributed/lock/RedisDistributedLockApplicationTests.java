package com.redis.distributed.lock;

import com.redis.distributed.lock.config.RedisConfig;
import com.redis.distributed.lock.utils.RedissonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisDistributedLockApplicationTests {

    @Autowired
    private RedisConfig redisConfig;

    @Test
    void contextLoads() {
        System.out.println(Long.MIN_VALUE);
        System.out.println(TimeUnit.SECONDS);

        new RedissonUtils().tryLock(redisConfig);


    }

}
