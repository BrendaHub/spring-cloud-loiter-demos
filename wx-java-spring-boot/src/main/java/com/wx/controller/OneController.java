package com.wx.controller;

import com.wx.bean.User;
import com.wx.config.AuthorTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 10:06 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@RestController
@RequestMapping("/echo")
@Slf4j
@EnableConfigurationProperties  // 使用这个注解，开启 这个注解功能， @ConfigurationProperties(prefix = "author")
// 一般这个注册写有有main方法的类上
public class OneController {
    @Autowired
    AuthorTestConfiguration authorTestConfiguration;
    @Autowired
    static WxMpService wxMpService;
    @Autowired
    static WxMpConfigStorage wxMpConfigStorage;

    @GetMapping("/mp")
    public ResponseEntity<Object> echo(){
        log.info("wxMpService is " + wxMpService + " config is " + wxMpConfigStorage);
//        try {
//            String acc = wxMpService.getAccessToken();
//            log.info("acc is {}", acc);
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
        // 加载配置文件的数据内容如下
        log.info("===================");
        log.info("加载配置文件内容如下：");
        log.info(authorTestConfiguration.getName() + "  " + authorTestConfiguration.getAge());
        return new ResponseEntity<Object>(new String("WxJava"), HttpStatus.OK);
    }
}
