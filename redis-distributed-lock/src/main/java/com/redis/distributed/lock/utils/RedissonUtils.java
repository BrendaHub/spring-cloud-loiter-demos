package com.redis.distributed.lock.utils;

import com.redis.distributed.lock.config.RedisConfig;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @Fun Description //TODO
 * @Date 2020/6/16 15:26 16
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class RedissonUtils {

    @Autowired
    private RedisConfig redisConfig;

    public void tryLock(RedisConfig redisConfig) {
        // 1. 配置文件
        Config config = new Config();
        System.out.println(redisConfig.getHost()+" : " + redisConfig.getPort() + ">>" + redisConfig.getPassword());
        SingleServerConfig singleServerConfig = config.useSingleServer();
        StringBuilder sb = new StringBuilder("redis://");
        sb.append(redisConfig.getHost());
        sb.append(":");
        sb.append(redisConfig.getPort());
        singleServerConfig.setAddress(sb.toString());
        if ("".equals(redisConfig.getPassword())) {
            ;
        } else {
            singleServerConfig.setPassword(redisConfig.getPassword());
        }
        singleServerConfig.setDatabase(0);
        // 2. 构建RedissonClient
        RedissonClient redissonClient = Redisson.create(config);

        // 3. 设置锁定资源名称
        RLock lock = redissonClient.getLock("redLock");

        boolean isLock ;

        try {
            isLock = lock.tryLock(500, 30000, TimeUnit.MILLISECONDS);
            if (isLock) {
                // TODO 成功获取到锁， 执行业务逻辑
                System.out.println("获取业务锁， 执行相应的业务逻辑");
                Thread.sleep(15000);

            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            // 解锁操作
            lock.unlock();
        }

    }

    public static void main(String[] args) {
//        new RedissonUtils().tryLock();
        System.exit(0);
    }
}
