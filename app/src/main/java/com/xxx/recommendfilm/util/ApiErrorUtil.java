package com.xxx.recommendfilm.util;

import android.util.Log;

import com.xxx.recommendfilm.MainApplication;
import com.xxx.recommendfilm.manager.api.base.ApiException;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Consumer;

public class ApiErrorUtil {
    
   public static <T>SingleTransformer<T,T> dealError(){
       return new SingleTransformer<T,T>() {

           @Override
           public SingleSource<T> apply(Single<T> upstream) {
               return upstream.doOnError(new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable){
                       if (throwable instanceof ApiException) {
                           MainApplication.getApplication().showToast(((ApiException)throwable).getErrorMsg());
                       }else {
                           Log.d("RxThrowable",throwable.getMessage());
                       }

                   }
               });
           }
       };
    }
}
