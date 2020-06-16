package com.wx;

import com.wx.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 19:17 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Test
    public void test() {
        System.out.println(RedisUtils.get());
    }
}
