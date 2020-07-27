package com.xxx.recommendfilm.manager.api;

import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.login.LoginRequestModel;
import com.xxx.recommendfilm.model.login.LoginResultModel;
import com.xxx.recommendfilm.model.register.RegisterRequestModel;
import com.xxx.recommendfilm.model.register.RegisterResultModel;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //电影相关------------------------------------------------------------------------------------------------------------------------------------
    //分类检索电影基本信息
    @GET("movie/classify")
    Single<ResultModel<String>> register(@Query("classify") String classify);



    //通知相关------------------------------------------------------------------------------------------------------------------------------------





    //影圈相关------------------------------------------------------------------------------------------------------------------------------------





    //账户相关------------------------------------------------------------------------------------------------------------------------------------
    //登录
    @POST("account/sign/in")
    Single<ResultModel<LoginResultModel>> login(@Body LoginRequestModel loginRequestModel);

    //注册
    @POST("account/sign/up")
    Single<ResultModel<RegisterResultModel>> register(@Body RegisterRequestModel registerRequestModel);


}