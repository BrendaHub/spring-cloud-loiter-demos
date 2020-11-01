package com.provider.springcloudnacosprovider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/hello")
@Slf4j
public class SpringCloudNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosProviderApplication.class, args);
    }

    @GetMapping(value = "/info/{name}")
    public ResponseEntity<Result> hello(@PathVariable("name") String name) {
        Result<String> result = new Result<String>("1000", "test info", "Hello, " + name);
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }

    @GetMapping("/echo")
    public String info(@RequestParam String name) {
        log.info("request  param name  is {} ", name);
        return "Hello, " + name;
    }

}

@Data
@AllArgsConstructor
class Result<T> {
    private String code;
    private String message;
    private T t;

}
