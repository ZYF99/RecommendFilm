package com.xxx.recommendfilm.util;

import android.util.Log;

import com.xxx.recommendfilm.MainApplication;
import com.xxx.recommendfilm.manager.api.base.ApiException;
import com.xxx.recommendfilm.manager.api.base.ServerException;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Consumer;

public class ApiErrorUtil {

    public static <T> SingleTransformer<T, T> dealError() {
        return upstream -> upstream.doOnError(throwable -> {
            if (throwable instanceof ApiException) {
                MainApplication.getApplication().showToast(((ApiException) throwable).getErrorMsg());
            } else if (throwable instanceof ServerException) {
                MainApplication.getApplication().showToast(((ServerException) throwable).getErrorMsg());
            } else {
                Log.d("RxThrowable", throwable.getMessage());
            }
        });
    }
}
