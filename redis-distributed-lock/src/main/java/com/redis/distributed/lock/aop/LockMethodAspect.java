package com.redis.distributed.lock.aop;

import com.redis.distributed.lock.annotation.RedisLock;
import com.redis.distributed.lock.utils.JedisUtil;
import com.redis.distributed.lock.utils.RedisLockHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @Fun Description //TODO AOP 拦截器
 * @Date 2020/6/16 16:27 16
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Aspect
@Component
public class LockMethodAspect {

    @Autowired
    private RedisLockHelper redisLockHelper;

    @Autowired
    private JedisUtil jedisUtil;

    private Logger logger = LoggerFactory.getLogger(LockMethodAspect.class);

    @Around("@annotation(com.redis.distributed.lock.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint joinPoint) {
        Jedis jedis = jedisUtil.getJedis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedisLock redisLock = method.getAnnotation(RedisLock.class);

        String value = UUID.randomUUID().toString();
        String key = redisLock.key();

        try {
//            final boolean isLock = redisLockHelper.lock(jedis, key, value, redisLock.expire(), redisLock.timeunit());
//            final boolean isLock = redisLockHelper.lock_with_lua(jedis, key, value, redisLock.expire());
            final boolean isLock = redisLockHelper.lock_with_waitTime(jedis, key, value, redisLock.expire(), redisLock.waitTime(), redisLock.timeunit());
            logger.info(" isLock : {}", isLock);
            if (!isLock) {
                logger.error("获得锁失败");
                throw new RuntimeException("获取锁失败");
            }
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            logger.info("释放锁");
            redisLockHelper.unlock(jedis, key, value);
            jedis.close();
        }
    }
}
