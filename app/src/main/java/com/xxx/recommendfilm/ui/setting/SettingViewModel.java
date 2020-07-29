package com.xxx.recommendfilm.ui.setting;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.MainApplication;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.mine.UpdateUserProfileRequestModel;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class SettingViewModel extends BaseViewModel {

    public MutableLiveData<UserProfile> userProfileLiveData = new MutableLiveData();

    //拉取我的个人信息
    public void getUserProfile() {
        bindLife(
                apiService.getUserProfile()
                        .compose(RxUtil.<ResultModel<UserProfile>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<UserProfile>>dealError())
                        .compose(this.<ResultModel<UserProfile>>autoProgressDialog())
                        .doOnSuccess(new Consumer<ResultModel<UserProfile>>() {
                            @Override
                            public void accept(ResultModel<UserProfile> userProfile) {
                                userProfileLiveData.postValue(userProfile.getData());
                            }
                        })
        );
    }

    //更新我的个人信息
    public void updateUserProfile(
            String avatar,
            String background,
            Long birthday,
            String gender,
            String nikeName,
            String signature
    ) {
        bindLife(
                apiService.updateUserProfile(new UpdateUserProfileRequestModel(avatar, background, birthday, gender, nikeName, signature))
                        .compose(ApiErrorUtil.<ResponseBody>dealError())
                        .compose(RxUtil.<ResponseBody>switchThread())
                        .compose(this.<ResponseBody>autoProgressDialog())
                        .doOnSuccess(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) {
                                MainApplication.getApplication().showToast("更新成功");
                            }
                        })
        );
    }

}
