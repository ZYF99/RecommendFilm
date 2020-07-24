package com.xxx.recommendfilm.ui.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoginViewModel extends BaseViewModel {

    MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>(false);

    void sdd() {

        apiService.login().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        isLoginSuccess.postValue(true);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isLoginSuccess.postValue(true);
                    }
                })
                .subscribe();

    }

}
