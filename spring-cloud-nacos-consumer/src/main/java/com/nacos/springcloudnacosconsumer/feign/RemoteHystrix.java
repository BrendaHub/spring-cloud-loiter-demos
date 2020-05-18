package com.nacos.springcloudnacosconsumer.feign;

import org.springframework.stereotype.Component;

@Component
public class RemoteHystrix implements INacosProviderClient {
    @Override
    public String info(String name) {
        return "请求超时了" + name ;
    }
}
