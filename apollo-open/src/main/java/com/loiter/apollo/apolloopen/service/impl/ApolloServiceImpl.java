package com.loiter.apollo.apolloopen.service.impl;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.loiter.apollo.apolloopen.okhttp3.HttpClientOkHttp3;
import com.loiter.apollo.apolloopen.service.ApolloService;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author loiter
 * @date 2020/10/27 14:52
 * @description
 */
@Service
public class ApolloServiceImpl implements ApolloService {

    @Override
    public String getAppIds(String url) {
        HttpClientOkHttp3 httpClientOkHttp3 = new HttpClientOkHttp3();
        String s = null;
        try {
            s = httpClientOkHttp3.get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public String getAppIdsByClient() {
        String portalUrl = "http://localhost:8070"; // portal url
        String token = "356ca0f14ffa59317ef8fe612bf905a4d99c8461"; // 申请的token
        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
                .withPortalUrl(portalUrl)
                .withToken(token)
                .build();
        return null;
    }


}
