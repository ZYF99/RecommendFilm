package com.xxx.recommendfilm;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.xxx.recommendfilm.manager.di.AppModule;
import com.xxx.recommendfilm.manager.di.component.AppComponent;
import com.xxx.recommendfilm.manager.di.component.DaggerAppComponent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainApplication extends Application {
    private static MainApplication app;
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        app = this;
        //初始化全局AppComponent,主要对module里面单利和app处于一个生命周期
        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public static MainApplication getApplication() {
        return app;
    }

    public static Context getAppContext() {
        return app.getApplicationContext();
    }

    public void showToast(String s) {
        AndroidSchedulers.mainThread().scheduleDirect(() -> Toast.makeText(MainApplication.getAppContext(), s, Toast.LENGTH_SHORT).show());
    }

}
