package com.redis.distributed.lock.annotation;

import ch.qos.logback.core.util.TimeUtil;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Fun Description //TODO redis distribute lock
 * @Date 2020/6/16 14:45 16
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    // 业务键
    String key();

    // 锁过期时间， 秒， 默认5秒
    int expire() default 5;

    // 尝试加锁， 最多等待时间， -9223372036854775808
    long waitTime() default Long.MIN_VALUE;

    // 锁超时间单位， 默认为秒
    TimeUnit timeunit() default TimeUnit.SECONDS;
}
