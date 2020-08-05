package com.xxx.recommendfilm.ui.login;


import androidx.lifecycle.MutableLiveData;
import com.orhanobut.hawk.Hawk;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.login.LoginRequestModel;
import com.xxx.recommendfilm.model.login.LoginResultModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import static com.xxx.recommendfilm.Constants.KEY_ACCOUNT;
import static com.xxx.recommendfilm.Constants.KEY_PASSWORD;
import static com.xxx.recommendfilm.Constants.KEY_TOKEN;
import static com.xxx.recommendfilm.Constants.KEY_UID;

public class LoginViewModel extends BaseViewModel {

    //MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>(false);
    public MutableLiveData<String> account = new MutableLiveData<>(Hawk.<String>get(KEY_ACCOUNT));
    public MutableLiveData<String> password = new MutableLiveData<>(Hawk.<String>get(KEY_PASSWORD));

    void login(Action action) {
        bindLife(
                apiService.login(new LoginRequestModel(account.getValue(), password.getValue()))
                        .compose(RxUtil.<ResultModel<LoginResultModel>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<LoginResultModel>>dealError())
                        .compose(this.<ResultModel<LoginResultModel>>autoProgressDialog())
                        .doOnSuccess(loginResultModel -> {
                            //isLoginSuccess.postValue(true);
                            Hawk.put(KEY_TOKEN, loginResultModel.getData().getToken());
                            Hawk.put(KEY_UID, loginResultModel.getData().getUid());
                            Hawk.put(KEY_ACCOUNT, account.getValue());
                            Hawk.put(KEY_PASSWORD, password.getValue());
                            action.run();
                        })
        );
    }

}
