package com.xxx.recommendfilm.manager.api;

import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.login.LoginRequestModel;
import com.xxx.recommendfilm.model.login.LoginResultModel;
import com.xxx.recommendfilm.model.mine.UpdateUserProfileRequestModel;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.model.register.RegisterRequestModel;
import com.xxx.recommendfilm.model.register.RegisterResultModel;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    //电影相关------------------------------------------------------------------------------------------------------------------------------------
    //分类检索电影基本信息
    @GET("movie/classify")
    Single<ResultModel<List<Film>>> fetchFilmListByClassify(@Query("classify") String classify);



    //通知相关------------------------------------------------------------------------------------------------------------------------------------





    //影圈相关------------------------------------------------------------------------------------------------------------------------------------

    //获取全部影圈
    @GET("moments")
    Single<ResultModel<List<Moment>>> fetchMomentList();

    //获取我发布的影圈
    @GET("mymoments")
    Single<ResultModel<List<Moment>>> fetchMyMomentList();




    //账户相关------------------------------------------------------------------------------------------------------------------------------------
    //登录
    @POST("account/sign/in")
    Single<ResultModel<LoginResultModel>> login(@Body LoginRequestModel loginRequestModel);

    //注册
    @POST("account/sign/up")
    Single<ResultModel<RegisterResultModel>> register(@Body RegisterRequestModel registerRequestModel);

    //获取用户个人信息
    @GET("account/profile")
    Single<ResultModel<UserProfile>> getUserProfile();

    //更新用户个人信息
    @PUT("account/profile")
    Single<ResponseBody> updateUserProfile(@Body UpdateUserProfileRequestModel updateUserProfileRequestModel);


}