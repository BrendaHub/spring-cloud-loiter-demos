package com.redis.distributed.lock.utils;

import com.redis.distributed.lock.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Fun Description //TODO
 * @Date 2020/6/16 15:03 16
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Component
public class JedisUtil {

    @Autowired
    private RedisConfig redisProperties;

    private Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    // 线程安全map
    private Map<String, JedisPool> mapPool = new ConcurrentHashMap<>();

    private JedisPool getPool() {
        String key = redisProperties.getHost() + ":" + redisProperties.getPort();
        JedisPool jedisPool;
        if (!mapPool.containsKey(key)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(redisProperties.getMax_idle());
            config.setMaxWaitMillis(redisProperties.getMax_wait());
            //在获取连接的时候检查有效性, 默认false
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            jedisPool = new JedisPool(config, redisProperties.getHost(),
                    redisProperties.getPort(),
                    redisProperties.getTimeout());
            mapPool.put(key, jedisPool);
        } else {
            jedisPool = mapPool.get(key);
        }
        return jedisPool;
    }

    public Jedis getJedis() {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                jedis = getPool().getResource();
                count++;
            } catch (Exception ex) {
                logger.error("get jedis failed", ex);
                if (jedis != null) {
                    jedis.close();
                }
            }

        } while (jedis == null && count < redisProperties.getRetry_num());
        return jedis;
    }

}
