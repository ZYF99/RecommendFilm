package com.xxx.recommendfilm.ui.login;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.manager.api.base.ResultModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>(false);

    void sdd() {

        bindLife(
                apiService.login()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(new Consumer<ResultModel<String>>() {
                            @Override
                            public void accept(ResultModel<String> stringResultModel) {
                                isLoginSuccess.postValue(true);
                            }
                        })
                        .compose(ApiErrorUtil.<ResultModel<String>>dealError())
        );


    }

}
