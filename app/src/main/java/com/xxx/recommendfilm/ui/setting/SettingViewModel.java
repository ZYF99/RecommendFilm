package com.xxx.recommendfilm.ui.setting;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.MainApplication;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.mine.UpdateUserProfileRequestModel;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class SettingViewModel extends BaseViewModel {

    public MutableLiveData<UserProfile> userProfileLiveData = new MutableLiveData();
    public MutableLiveData<String> nikeNameLiveData = new MutableLiveData();
    public MutableLiveData<String> genderLiveData = new MutableLiveData();
    public MutableLiveData<Uri> avatarLocalUriLiveData = new MutableLiveData();
    public MutableLiveData<Uri> backgroundLocalUrlLiveData = new MutableLiveData();
    public MutableLiveData<String> signatureLiveData = new MutableLiveData();

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
                                genderLiveData.postValue(userProfile.getData().getGender());
                                nikeNameLiveData.postValue(userProfile.getData().getNikeName());
                                signatureLiveData.postValue(userProfile.getData().getSignature());
                            }
                        })
        );
    }

    //更新我的个人信息
    public void updateUserProfile(
            String avatar,
            String background,
            Long birthday
    ) {
        bindLife(
                apiService.updateUserProfile(new UpdateUserProfileRequestModel(avatar, background, birthday, genderLiveData.getValue(), nikeNameLiveData.getValue(), signatureLiveData.getValue()))
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
