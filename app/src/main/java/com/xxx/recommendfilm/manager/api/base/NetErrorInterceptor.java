package com.xxx.recommendfilm.manager.api.base;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class NetErrorInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();
        assert responseBody != null;
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE);
        } catch (IOException e){
            return response;
        }
        Charset charset = Objects.requireNonNull(responseBody.contentType()).charset(StandardCharsets.UTF_8);

        if (contentLength != 0L) {
            assert charset != null;
            String responseStr = source.getBuffer().clone().readString(charset);
            try {
                JSONObject jsonObject = new JSONObject(responseStr);
                int statusCode = jsonObject.getInt("status");
                String errorMsg = jsonObject.getString("msg");
                if (statusCode != 200) {
                    throw new ApiException(statusCode, errorMsg);
                }
            } catch ( JSONException e) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(responseStr);
                    //解析不成 status:"",msg:"",data:"" 说明是服务器报错
                    int statusCode = jsonObject.getInt("status");
                    String errorMsg = jsonObject.getString("message");
                    throw new ServerException(statusCode,errorMsg);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }
        return response;

    }


}
