package com.xxx.recommendfilm.ui.register;


import androidx.lifecycle.MutableLiveData;

import com.orhanobut.hawk.Hawk;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.register.RegisterRequestModel;
import com.xxx.recommendfilm.model.register.RegisterResultModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import io.reactivex.functions.Consumer;
import static com.xxx.recommendfilm.Constants.KEY_ACCOUNT;
import static com.xxx.recommendfilm.Constants.KEY_PASSWORD;
import static com.xxx.recommendfilm.Constants.KEY_TOKEN;
import static com.xxx.recommendfilm.Constants.KEY_UID;

public class RegisterViewModel extends BaseViewModel {

    MutableLiveData<Boolean> isRegisterSuccess = new MutableLiveData<>(false);
    public MutableLiveData<String> nickName = new MutableLiveData<>("");
    public MutableLiveData<String> account = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");

    void registerAndLogin() {
        bindLife(
                apiService.register(new RegisterRequestModel(
                        account.getValue(),
                        "https://w.wallha",
                        "M",
                        nickName.getValue(),
                        password.getValue(),
                        "User"
                )).compose(RxUtil.<ResultModel<RegisterResultModel>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<RegisterResultModel>>dealError())
                        .compose(this.<ResultModel<RegisterResultModel>>autoProgressDialog())
                        .doOnSuccess(new Consumer<ResultModel<RegisterResultModel>>() {
                            @Override
                            public void accept(ResultModel<RegisterResultModel> registerResultModelResultModel) {
                                isRegisterSuccess.postValue(true);
                                Hawk.put(KEY_TOKEN, registerResultModelResultModel.getData().getToken());
                                Hawk.put(KEY_UID, registerResultModelResultModel.getData().getUid());
                                Hawk.put(KEY_ACCOUNT, account.getValue());
                                Hawk.put(KEY_PASSWORD, password.getValue());
                            }
                        })
        );
    }

}
