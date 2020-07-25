package com.xxx.recommendfilm;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.xxx.recommendfilm.manager.di.ApiModule;
import com.xxx.recommendfilm.manager.di.component.AppComponent;
import com.xxx.recommendfilm.manager.di.component.DaggerAppComponent;

public class MainApplication extends Application {
    private static MainApplication app;
    public AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        app=this;
        //初始化全局AppComponent,主要对module里面单利和app处于一个生命周期
        appComponent = DaggerAppComponent.builder().apiModule(new ApiModule()).build();
    }

    public static MainApplication getApplication(){
        return app;
    }

    public static Context getAppContext(){
        return app.getApplicationContext();
    }

    public void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

}
