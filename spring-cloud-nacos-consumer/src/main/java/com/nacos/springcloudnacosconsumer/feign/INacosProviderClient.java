package com.nacos.springcloudnacosconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "spring-nacos-provider", fallback = RemoteHystrix.class)
public interface INacosProviderClient {
    @GetMapping("/hello/echo")
    String info(@RequestParam String name);
}
