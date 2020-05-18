package com.nacos.springcloudnacosconsumer.controller;

import com.nacos.springcloudnacosconsumer.feign.INacosProviderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {
    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private INacosProviderClient iNacosProviderClient;


    @GetMapping("/feign")
    public String test(@RequestParam String name) {
        return iNacosProviderClient.info(name);
    }


}
