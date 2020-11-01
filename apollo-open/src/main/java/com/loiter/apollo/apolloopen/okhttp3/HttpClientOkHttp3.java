package com.loiter.apollo.apolloopen.okhttp3;

import okhttp3.*;

import java.io.IOException;

/**
 * @author loiter
 * @date 2020/10/27 14:49
 * @description
 */
public class HttpClientOkHttp3 {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
    /*  Call call = client.newCall(request);
      call.*/

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
