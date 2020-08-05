package com.xxx.recommendfilm.manager.api;

import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.UploadImageResultModel;
import com.xxx.recommendfilm.model.film.ClassifyListModel;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.film.FilmPageModel;
import com.xxx.recommendfilm.model.login.LoginRequestModel;
import com.xxx.recommendfilm.model.login.LoginResultModel;
import com.xxx.recommendfilm.model.mine.UpdateUserProfileRequestModel;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.model.register.RegisterRequestModel;
import com.xxx.recommendfilm.model.register.RegisterResultModel;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    //电影相关------------------------------------------------------------------------------------------------------------------------------------

    //拉取电影全部分类列表
    @GET("movie/classify/all")
    Single<ResultModel<ClassifyListModel>> fetchFilmClassifyList();

    //按分类检索电影基本信息
    @GET("movie/classify")
    Single<ResultModel<FilmPageModel>> fetchFilmListByClassify(
            @Query("classify") String classify,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );

    //按名称检索电影列表
    @GET("movie/name")
    Single<ResultModel<FilmPageModel>> fetchFilmListByName(
            @Query("name") String name,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );


    //按mid检索电影详细信息
    @GET("movie/mid")
    Single<ResultModel<Film>> fetchFilmDetailInfo(@Query("mid") Long mid);



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


    //图片相关--------------------------------------------------------------------------------------------------------------------------------------
    //上传头像
    @Multipart
    @POST("tools/upload")
    Single<ResultModel<UploadImageResultModel>> upLoadImage(@Part MultipartBody.Part file);

}