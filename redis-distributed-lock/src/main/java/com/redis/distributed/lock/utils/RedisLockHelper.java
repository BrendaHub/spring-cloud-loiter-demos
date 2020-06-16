package com.redis.distributed.lock.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Fun Description //TODO
 * @Date 2020/6/16 15:37 16
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Component
public class RedisLockHelper {

    private long sleepTime = 100;

    // 直接使用setnx + expire 方式获取分布式锁  setnx 和 expire 二步操作非原子性
    public boolean lock_setnx(Jedis jedis, String key, String value , int timeout) {
        Long result = jedis.setnx(key, value);
        // result = 1 时， 设置成功， 否则设置失败
        if (result == 1L) {
            return jedis.expire(key, timeout) == 1l;
        } else {
            return false;
        }
    }

    /// 使用lua 脚本 ， 脚本中使用 setnx + expier 命令进行加锁操作 原子性
    public boolean lock_with_lua (Jedis jedis , String key , String UniqueId,  int timeout )  {
        String lua_script = " if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then " +
                " redis.call('expire', KEYS[1], ARGV[2]) return 1 else return 0 end ";

        List<String> keys = new ArrayList();
        List<String> values = new ArrayList<>();
        keys.add(key);
        values.add(UniqueId);
        values.add(String.valueOf(timeout));

        Object result = jedis.eval(lua_script, keys, values);

        // 判断结果
        return result.equals(1l);
    }

    // 在Redis的2.6.12及以后中,使用 set key value [NX] [EX] 命令 , 以下是3.x版本的写法
    public boolean lock(Jedis jedis, String key, String value, int timeout, TimeUnit timeUnit) {
        long seconds = timeUnit.toSeconds(timeout);
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(new Long(seconds).intValue());
        return "OK".equals(jedis.set(key, value, setParams));
    }

    // 自定义获取锁的超时时间, 自旋式的在等待同一资源的锁
    public boolean lock_with_waitTime(Jedis jedis, String key, String value, int timeout, long waitTime, TimeUnit timeUnit) {
        long seconds = timeUnit.toSeconds(timeout);
        while (waitTime >= 0) {
            SetParams setParams = new SetParams();
            setParams.nx();
            setParams.ex(new Long(seconds).intValue());
            String result = jedis.set(key, value, setParams);
            if ("OK".equals(result)) {
                return true;
            }

            waitTime -= sleepTime;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 暴力删除锁， 不建议使用
    public void unlock_with_del(Jedis jedis, String key ) {
        jedis.del(key);
    }

    // 采用lua 脚 本 释放分布式锁
    public boolean unlock(Jedis jedis, String key, String value ) {
        String luaScript = " if redis.call('get', KEYS[1]) == ARGV[1] then " +
                " return redis.call('del', KEYS[1]) else return 0 end ";
        return jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }
}
