package com.xxx.recommendfilm.ui.setting;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.BuildConfig;
import com.xxx.recommendfilm.model.mine.UpdateUserProfileRequestModel;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import java.io.File;
import io.reactivex.functions.Action;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class SettingViewModel extends BaseViewModel {

    public MutableLiveData<UserProfile> userProfileLiveData = new MutableLiveData();
    public MutableLiveData<String> nikeNameLiveData = new MutableLiveData();
    public MutableLiveData<String> genderLiveData = new MutableLiveData();
    public MutableLiveData<Long> birthdayLongLiveData = new MutableLiveData();
    public MutableLiveData<String> avatarLocalPathLiveData = new MutableLiveData();
    public MutableLiveData<String> backgroundLocalPathLiveData = new MutableLiveData();
    public MutableLiveData<String> signatureLiveData = new MutableLiveData();

    //拉取我的个人信息
    public void getUserProfile() {
        bindLife(
                apiService.getUserProfile()
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSuccess(userProfile -> {
                            userProfileLiveData.postValue(userProfile.getData());
                            avatarLocalPathLiveData.postValue(userProfile.getData().getAvatar());
                            backgroundLocalPathLiveData.postValue(userProfile.getData().getBackground());
                            genderLiveData.postValue(userProfile.getData().getGender());
                            nikeNameLiveData.postValue(userProfile.getData().getNikeName());
                            signatureLiveData.postValue(userProfile.getData().getSignature());
                        })
        );
    }

    //更新我的个人信息
    public void updateUserProfile(final Action onUpdated) {
        bindLife(
                apiService.updateUserProfile(new UpdateUserProfileRequestModel(
                        avatarLocalPathLiveData.getValue(),
                        backgroundLocalPathLiveData.getValue(),
                        birthdayLongLiveData.getValue(),
                        genderLiveData.getValue(),
                        nikeNameLiveData.getValue(),
                        signatureLiveData.getValue())
                ).compose(ApiErrorUtil.<ResponseBody>dealError())
                        .compose(RxUtil.<ResponseBody>switchThread())
                        .compose(this.<ResponseBody>autoProgressDialog())
                        .doOnSuccess(responseBody -> {
                            try {
                                onUpdated.run();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    //上传头像
    public void uploadAvatar(File file) {
        if (file != null) {
            RequestBody photoRequestBody =
                    RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData(
                    "imageFile",
                    file.getName(),
                    photoRequestBody
            );
            bindLife(apiService.upLoadImage(photo)
                    .compose(ApiErrorUtil.dealError())
                    .compose(RxUtil.switchThread())
                    .compose(this.autoProgressDialog())
                    .doOnSuccess(result -> avatarLocalPathLiveData.postValue(BuildConfig.BASE_URL + "/image/" + result.getData().getImagePath())));


        }
    }

    //上传背景
    public void uploadBackground(File file) {
        if (file != null) {
            RequestBody photoRequestBody =
                    RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData(
                    "imageFile",
                    file.getName(),
                    photoRequestBody
            );
            bindLife(apiService.upLoadImage(photo)
                    .compose(ApiErrorUtil.dealError())
                    .compose(RxUtil.switchThread())
                    .compose(this.autoProgressDialog())
                    .doOnSuccess(result -> backgroundLocalPathLiveData.postValue(BuildConfig.BASE_URL + "/image/" + result.getData().getImagePath())));
        }
    }

}
