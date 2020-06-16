package com.wx.config;

import com.wx.bean.User;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 14:49 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
//@Configuration
@Slf4j
public class MainConfiguration {

    /**
     * @Value三种情况的用法。
     * $是去找外部配置的参数，将值赋过来
     * #是SpEL表达式，去寻找对应变量的内容
     * 直接写字符串就是将字符串的值注入进去
     **/
    @Value("${wx.mp.appId}")
    private String appId;

    /***
     * @Value 三种情况用法说明：
     * 1、$ 是去找外部配置参数， 将值赋予进来
     * 2、# 是SpEL表达式， 去寻找对应变量的内容
     * 3、直接写字符串就是将字符串的值注入进去
     **/
    @Value("${wx.mp.secret}")
    private String appSecret;

    @Value("${wx.mp.token}")
    private String token;

    @Value("${wx.mp.aesKey}")
    private String aesKey;


//    @Bean
//    public WxMpConfigStorage wxMpConfigStorage() {
//        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
//        log.info("config appID is {}", this.appId);
//        configStorage.setAppId(this.appId);
//        configStorage.setSecret(this.appSecret);
//        configStorage.setToken(this.token);
//        configStorage.setAesKey(this.aesKey);
//        log.info("实例化对象为： {}", configStorage);
//        return configStorage;
//    }
//
//    @Bean
//    public WxMpService wxMpService() {
//        WxMpService wxMpService = new WxMpServiceImpl();
//        log.info("wxMpConfigStorage 为， {}", wxMpConfigStorage());
//        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
//        return wxMpService;
//    }

    @Bean
    public User user() {
        return new User();
    }
}
