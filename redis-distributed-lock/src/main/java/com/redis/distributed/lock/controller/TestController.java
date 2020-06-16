package com.redis.distributed.lock.controller;

import com.redis.distributed.lock.annotation.RedisLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Fun Description //TODO
 * @Date 2020/6/16 15:00 16
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@RestController
public class TestController {

    @RedisLock(key = "redis_lock", expire = 60, timeunit = TimeUnit.SECONDS, waitTime = 30)
    @GetMapping("/index")
    public String index() {
        try {
            Thread.sleep(15000);
            System.out.println(" 暂停 。。。。 ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "index, Hello";
    }

    @RedisLock(key = "redis_lock", expire = 60, timeunit = TimeUnit.SECONDS, waitTime = 30)
    @GetMapping("/hello")
    public String indexTwo() {
        System.out.println(" 暂停 。。。。 2222");
        return "Hello Index Two ";
    }

    @RedisLock(key = "redis_lock_three", expire = 100000, timeunit = TimeUnit.SECONDS, waitTime = 30)
    @GetMapping("/three")
    public String indexTree() {
        System.out.println(" 暂停 。。。。 3333 ");
        return "Hello nx lock ";
    }
}
