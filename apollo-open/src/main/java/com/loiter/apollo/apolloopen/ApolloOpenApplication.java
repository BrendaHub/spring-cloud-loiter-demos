package com.loiter.apollo.apolloopen;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

@SpringBootApplication
@Slf4j
@RetrofitScan("com.loiter.apollo.apolloopen.retrofit")
public class ApolloOpenApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApolloOpenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String portalUrl = "http://localhost:8070"; // portal url
        String token = "e16e5cd903fd0c97a116c873b448544b9d086de9"; // 申请的token
        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
                .withPortalUrl(portalUrl)
                .withToken(token)
                .build();

        Assert.notNull(client, "clien is not null");
        log.info(" client is {}", client);
    }
}
