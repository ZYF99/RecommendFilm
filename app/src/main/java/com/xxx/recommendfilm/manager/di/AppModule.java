package com.xxx.recommendfilm.manager.di;

import com.google.gson.Gson;
import com.xxx.recommendfilm.BuildConfig;
import com.xxx.recommendfilm.manager.api.ApiService;
import com.xxx.recommendfilm.manager.api.base.ApiClient;
import com.xxx.recommendfilm.manager.api.base.HeaderInterceptor;
import com.xxx.recommendfilm.manager.api.base.NetErrorInterceptor;
import com.xxx.recommendfilm.util.DialogUtil;

import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class AppModule {

    @Singleton
    @Provides
    public ApiService provideApiService() {
        return provideApiClient().createService(ApiService.class);
    }

/*    @Singleton
    @Provides
    public DialogUtil provideDialogUtil() {
        return new DialogUtil();
    }*/

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    public ApiClient<ApiService> provideApiClient() {
        ApiClient.Builder apiClient = new ApiClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        apiClient.getOkBuilder().
                addInterceptor(new HeaderInterceptor())
                .addInterceptor(new NetErrorInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);
        return apiClient.build(BuildConfig.BASE_URL);
    }

}
