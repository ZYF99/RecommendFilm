package com.xxx.recommendfilm.ui.base;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.xxx.recommendfilm.MainApplication;
import com.xxx.recommendfilm.manager.api.ApiService;
import com.xxx.recommendfilm.manager.di.component.DaggerAppComponent;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

public class BaseViewModel extends ViewModel implements BindLife {

    @Inject
    public ApiService apiService;

    public BaseViewModel() {
        MainApplication.getApplication().appComponent.inject(this);
    }

    protected <T>void bindLife(Single<T> single){
        compositeDisposable.add(single.subscribe(new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("RxError",throwable.getMessage());
            }
        }));
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(){
        compositeDisposable.dispose();
        compositeDisposable.clear();
    }
}
