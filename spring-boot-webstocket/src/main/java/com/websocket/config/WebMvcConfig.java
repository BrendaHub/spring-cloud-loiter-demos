package com.websocket.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Fun Description //TODO
 * @Date 2020/6/4 00:31 04
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
public class WebMvcConfig implements  WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws01").setViewName("/ws01");
    }
//
//
//    /**
//     * 配置Spring支持的websocket的类，是必须的
//     *
//     * @return
//     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
}
