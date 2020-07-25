package com.xxx.recommendfilm.manager.api;


import com.xxx.recommendfilm.manager.api.base.ResultModel;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search")
    Single<ResultModel<String>> login();
}