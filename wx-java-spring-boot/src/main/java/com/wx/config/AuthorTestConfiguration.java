package com.wx.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 15:45 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Component
@Data
@ConfigurationProperties(prefix = "author")
// {"classpath:static/config/authorSetting.properties"},
@PropertySource(value = {"classpath:authorSetting.properties"},
        ignoreResourceNotFound = false, encoding = "UTF-8", name = "authorSetting.properties")
public class AuthorTestConfiguration {
    // 这个写法是没有引入 @ConfigurationProperties(prefix = "author") 这个注解的写法
//    @Value("${author.name}")
//    private String name;
//    @Value("${author.age}")
//    private int age;

     // 引入ConfigurationProperties(prefix = "author") 使写法更简单
    private String name;
    private int age;
}
